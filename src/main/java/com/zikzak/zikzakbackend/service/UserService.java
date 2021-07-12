package com.zikzak.zikzakbackend.service;

import com.zikzak.zikzakbackend.model.Role;
import com.zikzak.zikzakbackend.model.UserDto;
import com.zikzak.zikzakbackend.model.UserModel;
import com.zikzak.zikzakbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public void addUser(UserModel userModel) {
        String email = userModel.getEmail();

        if (isEmailUnique(email)) {
            userRepository.save(userModel);
        }
    }

    public UserModel userDtoToModel(UserDto userDto) {
        return UserModel.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(Role.CLIENT)
                .build();
    }

    public boolean isDtoValid(UserDto userDto) {
        return isEmailUnique(userDto.getEmail()) && passwordValid(userDto.getPassword());
    }

    private boolean isEmailUnique(String email) {
        if (userRepository.findByEmail(email).isEmpty()) return true;
        else throw new IllegalArgumentException("Email address already in use.");
    }

    private boolean passwordValid(String pw) {
        return pw.length() >= 8;
    }

}
