package com.project.smartplanai.entity;

import com.project.smartplanai.enums.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)//직접 생성하는거 방지하려고 씀
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;//

    //닉네임
    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;  // 관리자, 일반 사용자


    //비번 변경 메서드
    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
