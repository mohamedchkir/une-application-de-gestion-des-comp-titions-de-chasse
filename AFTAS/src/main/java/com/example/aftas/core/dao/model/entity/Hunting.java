package com.example.aftas.core.dao.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "number_of_fish")
    private Integer numberOfFish;

    @ManyToOne
    private Fish fish;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Competition competition;


}