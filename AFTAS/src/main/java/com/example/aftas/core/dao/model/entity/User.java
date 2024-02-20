package com.example.aftas.core.dao.model.entity;


import com.example.aftas.shared.Enum.IdentityDocument;
import com.example.aftas.shared.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class User  implements UserDetails {
    @Id
    private Integer num;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "accession_date")
    private LocalDate accessionDate;
    private String nationality;
    @Column(name = "identity_number", unique = true)
    private String identityNumber;
    @Column(name = "identity_document")
    private IdentityDocument identityDocument;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Hunting> huntingList;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Ranking> rankingList;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}