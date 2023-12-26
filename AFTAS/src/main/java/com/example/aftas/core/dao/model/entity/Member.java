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
    @Column(name = "family_name")
    private String familyName;
    @Column(name = "accession_date")
    private LocalDate accessionDate;
    private String nationality;
    @Column(name = "identity_number", unique = true)
    private String identityNumber;
    @Column(name = "identity_document")
    private IdentityDocument identityDocument;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Hunting> huntingList;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Ranking> rankingList;
}