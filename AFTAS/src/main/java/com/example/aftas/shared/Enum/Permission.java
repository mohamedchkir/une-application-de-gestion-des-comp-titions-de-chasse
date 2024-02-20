package com.example.aftas.shared.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    COMPETITION_READ("competition:read"),
    COMPETITION_MANAGE("competition:manage"),
    COMPETITION_EVALUATE("competition:evaluate"),

    USER_MANAGE("user:manage");

    private final String permission;
}
