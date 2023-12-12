package com.example.aftas.core.service;

import com.example.aftas.core.dao.model.entity.Ranking;
import com.example.aftas.core.dao.model.entity.RankingKey;

public interface RankingService {
    Ranking addRanking(Ranking ranking);

    Ranking getRankingById(RankingKey id);
}
