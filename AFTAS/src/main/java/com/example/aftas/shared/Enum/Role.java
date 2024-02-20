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
    USER(Collections.emptySet()),
    ADMIN(Set.of(
            ADMIN_READ,
            ADMIN_WRITE,
            ADMIN_DELETE,
            ADMIN_UPDATE
    )),
    SUPER_ADMIN(Set.of(
            SUPER_ADMIN_READ,
            SUPER_ADMIN_WRITE,
            SUPER_ADMIN_DELETE,
            SUPER_ADMIN_UPDATE,
            ADMIN_READ,
            ADMIN_WRITE,
            ADMIN_DELETE,
            ADMIN_UPDATE
    )
    );

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}