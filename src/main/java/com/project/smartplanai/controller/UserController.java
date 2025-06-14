package com.project.smartplanai.controller;

import com.project.smartplanai.entity.User;
import com.project.smartplanai.enums.Role;
import com.project.smartplanai.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String email = body.get("email");
        String password = body.get("password");
        if (userService.isUsernameTaken(username) || userService.isEmailTaken(email)) {
            return ResponseEntity.badRequest().body("duplicate");
        }
        userService.registerUser(username, email, password, Role.USER);
        return ResponseEntity.ok("registered");
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자 로그인 기능입니다.")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        String token = userService.login(body.get("username"), body.get("password"));
        if (token == null) {
            return ResponseEntity.status(401).body("invalid");
        }
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "유저정보조회", description = "특정 유져를 검색합니다")
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
