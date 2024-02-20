package com.example.aftas.core.dao.model.entity;

import com.example.aftas.shared.Enum.TokenType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @GeneratedValue
    private Integer id;
    private String token;
    private TokenType tokenType;
    private boolean expired;
    private boolean revoked;

    @ManyToOne
    @JoinColumn( name = "user_id")
    private User user;
}

