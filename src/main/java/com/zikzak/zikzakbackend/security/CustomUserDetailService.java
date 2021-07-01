package com.zikzak.zikzakbackend.security;

import com.zikzak.zikzakbackend.model.UserModel;
import com.zikzak.zikzakbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository users;

    @Autowired
    public CustomUserDetailService(UserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = users.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email: " + email + " not found"));

        return User.builder()
                .username(email)
                .password(user.getPassword())
                .authorities(user.getRole().name())
                .build();
    }

}
