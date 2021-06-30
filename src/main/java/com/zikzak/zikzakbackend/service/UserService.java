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

    public void addUser(UserDto userData) {
        String email = userData.getEmail();

        if (isEmailUnique(email)) {
            UserModel user = UserModel.builder()
                    .email(email)
                    .password(passwordEncoder.encode(userData.getPassword()))
                    .role(Role.valueOf(userData.getRoleName()))
                    .build();

            userRepository.save(user);
        }
    }

    private boolean isEmailUnique(String email) {
        if (userRepository.findByEmail(email).isEmpty()) return true;
        else throw new IllegalArgumentException("Email address already in use.");
    }

}
