package com.example.aftas.core.dao.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private User user;

    @ManyToOne
    private Competition competition;


}