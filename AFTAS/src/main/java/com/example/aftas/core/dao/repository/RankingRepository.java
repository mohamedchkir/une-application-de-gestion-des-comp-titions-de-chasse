package com.example.aftas.core.dao.repository;

import com.example.aftas.core.dao.model.entity.Competition;
import com.example.aftas.core.dao.model.entity.Ranking;
import com.example.aftas.core.dao.model.entity.RankingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankingKey> {
    List<Ranking> findByCompetition(Competition competition);
}
