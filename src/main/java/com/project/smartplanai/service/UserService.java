package com.project.smartplanai.service;

import com.project.smartplanai.entity.User;
import com.project.smartplanai.enums.Role;
import com.project.smartplanai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    // 사용자 ID로 조회
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    //사용자 명으로 조회
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 이메일 중복 체크
    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    // 사용자 로그인 시 비밀번호 확인 (예시)
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}


