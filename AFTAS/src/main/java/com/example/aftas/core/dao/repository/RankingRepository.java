package com.example.aftas.core.dao.repository;

import com.example.aftas.core.dao.model.entity.Ranking;
import com.example.aftas.core.dao.model.entity.RankingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankingKey> {
}
