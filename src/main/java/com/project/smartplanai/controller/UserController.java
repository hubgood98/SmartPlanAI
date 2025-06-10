package com.project.smartplanai.controller;

import com.project.smartplanai.entity.User;
import com.project.smartplanai.enums.Role;
import com.project.smartplanai.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
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
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        String token = userService.login(body.get("username"), body.get("password"));
        if (token == null) {
            return ResponseEntity.status(401).body("invalid");
        }
        return ResponseEntity.ok(token);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
