package com.cherrypick.order.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "token")
@Entity
public class Token {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accessToken;

    private String refreshToken;

    private Long memberId;

    public Token updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public Token updateAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public Token updateToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        return this;
    }


    @Builder
    public Token(Long id, String accessToken, String refreshToken, Long memberId) {
        this.id = id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.memberId = memberId;
    }
}