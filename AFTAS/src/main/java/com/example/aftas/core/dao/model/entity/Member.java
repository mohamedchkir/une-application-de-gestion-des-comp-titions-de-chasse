package com.example.aftas.core.dao.model.entity;


import com.example.aftas.shared.Enum.IdentityDocument;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Member {
    @Id
    private Integer num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    @Column(unique = true)
    private String identityNumber;
    private IdentityDocument identityDocument;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Hunting> huntingList;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Ranking> rankingList;
}