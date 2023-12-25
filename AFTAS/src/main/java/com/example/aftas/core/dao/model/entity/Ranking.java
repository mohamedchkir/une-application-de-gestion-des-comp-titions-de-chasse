package com.example.aftas.core.dao.model.entity;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class Ranking {
    @EmbeddedId
    private RankingKey id;
    private Integer rank;
    private Integer score;

    @ManyToOne
    private Competition competition;

    @ManyToOne
    private Member member;

}
