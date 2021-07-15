package com.cherrypick.order.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String nickName;

    private String password;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(Long id, String email, String name, String nickName, String password, String phone, Gender gender, Role role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
    }
}
