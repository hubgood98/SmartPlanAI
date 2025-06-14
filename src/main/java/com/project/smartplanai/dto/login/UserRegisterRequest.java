package com.project.smartplanai.dto.login;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    private String username;
    private String name;
    private String email;
    private String password;
}
