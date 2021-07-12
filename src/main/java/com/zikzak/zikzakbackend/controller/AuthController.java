package com.zikzak.zikzakbackend.controller;

import com.zikzak.zikzakbackend.model.Role;
import com.zikzak.zikzakbackend.model.UserDto;
import com.zikzak.zikzakbackend.model.UserModel;
import com.zikzak.zikzakbackend.model.validation.Validation;
import com.zikzak.zikzakbackend.security.JwtTokenServices;
import com.zikzak.zikzakbackend.service.EmailService;
import com.zikzak.zikzakbackend.service.UserService;
import com.zikzak.zikzakbackend.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = {"${development.url}", "${production.url}"}, allowCredentials = "true")
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenServices jwtTokenServices;
    private final ValidationService validationService;
    private final EmailService emailService;

    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${development.url}")
    private String hostUrl;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, UserService userService, ValidationService validationService, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenServices = jwtTokenServices;
        this.validationService = validationService;
        this.emailService = emailService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity signIn(@RequestBody UserDto userDto, HttpServletResponse httpServletResponse){
        try{
            String email = userDto.getEmail();
            String password = userDto.getPassword();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            Role role = Role.valueOf(authentication.getAuthorities().stream().findFirst().orElseThrow().getAuthority());
            String jwt = jwtTokenServices.createToken(email, role);
            Cookie cookie = new Cookie("jwt", jwt);
//            cookie.setSecure(true);
            cookie.setMaxAge(36000);
            cookie.setHttpOnly(true);
            httpServletResponse.addCookie(cookie);
            return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
        } catch (AuthenticationException authenticationException){
            throw new BadCredentialsException("Invalid email/password.");
        }
    }

    @PostMapping("/registration")
    public void signUp(@RequestBody UserDto userData) {
        if (!userService.isDtoValid(userData)) throw new BadCredentialsException("E-mail or password doesn't meet the requirements");
        UserModel userModel = userService.userDtoToModel(userData);
        userService.addUser(userModel);
        Validation validation = validationService.createValidationForUser(userModel.getId());
        validationService.saveValidation(validation);
        SimpleMailMessage email = this.createValidationEmail(userModel, validation);
        emailService.sendEmail(email);
    }

    @GetMapping("/user")
    public Map<String, Object> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("user", authentication.getPrincipal());
        String formattedRole = authentication.getAuthorities().stream().findFirst().orElseThrow().getAuthority().substring(5);
        userDetails.put("role", formattedRole);
        return userDetails;
    }

    @PostMapping("/validate/{validationCode}")
    public void validate(@PathVariable UUID validationCode) {
        validationService.deleteValidationByCode(validationCode);
    }

    private SimpleMailMessage createValidationEmail(UserModel userModel, Validation validation) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(userModel.getEmail());
        message.setSubject("Activate your email!");
        message.setFrom(fromEmail);
        message.setText("To confirm your registration, please click the following link : " + hostUrl + "/validate?k=" + validation.getValidationCode());

        return message;
    }

}