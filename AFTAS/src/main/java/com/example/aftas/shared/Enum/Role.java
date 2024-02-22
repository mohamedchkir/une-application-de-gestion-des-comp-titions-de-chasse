package com.example.aftas.shared.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.aftas.shared.Enum.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADHERENT(Set.of(
            COMPETITION_READ
    )),
    JURY(Set.of(
            COMPETITION_MANAGE,
            COMPETITION_EVALUATE,
            COMPETITION_READ,
            FISH_READ,
            FISH_WRITE,
            FISH_DELETE,
            HUNTING_READ,
            HUNTING_WRITE,
            LEVEL_READ,
            LEVEL_WRITE,
            RANKING_READ,
            RANKING_WRITE,
            USER_READ,
            USER_WRITE,
            USER_DELETE
    )),
    MANAGER(Set.of(
            COMPETITION_MANAGE,
            COMPETITION_EVALUATE,
            COMPETITION_READ,
            FISH_READ,
            FISH_WRITE,
            FISH_DELETE,
            HUNTING_READ,
            HUNTING_WRITE,
            LEVEL_READ,
            LEVEL_WRITE,
            RANKING_READ,
            RANKING_WRITE,
            USER_READ,
            USER_WRITE,
            USER_DELETE,
            USER_CHANGE_ROLE
    ));

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
