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
    ADHERENT(Collections.emptySet()),
    JURY(Set.of(
            COMPETITION_MANAGE,
            COMPETITION_EVALUATE
    )),
    MANAGER(Set.of(
            COMPETITION_MANAGE,
            COMPETITION_EVALUATE,
            USER_MANAGE
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
