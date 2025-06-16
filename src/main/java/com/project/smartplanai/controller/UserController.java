package com.project.smartplanai.controller;

import com.project.smartplanai.dto.login.LoginRequest;
import com.project.smartplanai.dto.login.UserRegisterRequest;
import com.project.smartplanai.entity.User;
import com.project.smartplanai.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@OpenAPIDefinition(tags = {})
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "사용자 회원가입 신청")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest dto) {

        if (userService.isUsernameTaken(dto.getUsername()) || userService.isEmailTaken(dto.getEmail())) {
            return ResponseEntity.badRequest().body("duplicate"); //하나라도 중복된다면 발생
        }
        userService.registerUser(dto);
        return ResponseEntity.ok("registered");
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자 로그인 기능입니다.")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        String token = userService.login(req.getUsername(),req.getPassword());
        if (token != null) {
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @Operation(summary = "유저정보조회", description = "특정 유져를 검색합니다")
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "아이디 중복확인", description = "특정 유져를 중복확인")
    @GetMapping("/check-username")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
        boolean exists = userService.isUsernameTaken(username);
        return ResponseEntity.ok(exists); // true면 이미 사용중, false면 사용가능
    }

    @Operation(summary = "이메일 중복확인", description = "특정 유져를 중복확인")
    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        boolean exists = userService.isEmailTaken(email);
        return ResponseEntity.ok(exists);
    }
}
