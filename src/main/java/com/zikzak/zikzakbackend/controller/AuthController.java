package com.zikzak.zikzakbackend.controller;

import com.zikzak.zikzakbackend.model.Role;
import com.zikzak.zikzakbackend.model.UserDto;
import com.zikzak.zikzakbackend.model.UserModel;
import com.zikzak.zikzakbackend.security.JwtTokenServices;
import com.zikzak.zikzakbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@CrossOrigin(origins = {"${development.url}", "${production.url}"}, allowCredentials = "true")
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenServices jwtTokenServices;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenServices = jwtTokenServices;
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

}