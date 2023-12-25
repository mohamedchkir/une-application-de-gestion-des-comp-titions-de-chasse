package com.example.aftas.core.dao.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Fish {
    @Id
    private String name;
    @Column(name = "average_weight")
    private Double averageWeight;

    @ManyToOne
    private Level level;

    @OneToMany(mappedBy = "fish")
    @ToString.Exclude
    private List<Hunting> huntingList;

}