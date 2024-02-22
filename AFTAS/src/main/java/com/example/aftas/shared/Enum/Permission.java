package com.example.aftas.shared.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    COMPETITION_READ("competition:read"),
    COMPETITION_MANAGE("competition:manage"),
    COMPETITION_EVALUATE("competition:evaluate"),

    // Fish
    FISH_READ("fish:read"),
    FISH_WRITE("fish:write"),
    FISH_DELETE("fish:delete"),


    // Hunting
    HUNTING_READ("hunting:read"),
    HUNTING_WRITE("hunting:write"),

    // Levels
    LEVEL_READ("level:read"),
    LEVEL_WRITE("level:write"),

    // Rankings
    RANKING_READ("ranking:read"),
    RANKING_WRITE("ranking:write"),

    // Users
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_DELETE("user:delete"),
    USER_CHANGE_ROLE("user:change_role");

    private final String permission;
}
