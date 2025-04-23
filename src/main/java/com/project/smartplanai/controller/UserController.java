package com.project.smartplanai.controller;

import com.project.smartplanai.entity.User;
import com.project.smartplanai.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
//
//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<String> addUser(@RequestBody User user) {
//        userService.registerUser(user);
//
//        return ResponseEntity.ok("회원가입 성공");
//    }
//
//    @GetMapping("{/username}")
//    public ResponseEntity<User> getUser(@PathVariable String username) {
//        Optional<User> user = userService.findByUsername(username);
//        return user.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}
