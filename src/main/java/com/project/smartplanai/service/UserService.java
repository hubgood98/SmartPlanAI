package com.project.smartplanai.service;

import com.project.smartplanai.entity.User;
import com.project.smartplanai.enums.Role;
import com.project.smartplanai.repository.UserRepository;
import com.project.smartplanai.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public User registerUser(String username, String email, String rawPassword, Role role) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User newUser = User.builder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .role(role)
                .build();
        return userRepository.save(newUser);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    public String login(String username, String rawPassword) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return jwtUtil.generateToken(username);
            }
        }
        return null;
    }
}
