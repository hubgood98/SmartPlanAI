package com.project.smartplanai.service;

import com.project.smartplanai.dto.login.UserRegisterRequest;
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

    public void registerUser(UserRegisterRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User newUser = User.builder()
                .username(dto.getUsername())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(encodedPassword)
                .role(Role.USER)
                .build();
        userRepository.save(newUser);
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
                return jwtUtil.createToken(user.getUsername(), user.getRole());
            }
        }
        return null;
    }
}
