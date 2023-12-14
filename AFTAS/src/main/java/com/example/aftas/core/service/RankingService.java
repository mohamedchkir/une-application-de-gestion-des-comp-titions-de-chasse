package com.example.aftas.core.service;

import com.example.aftas.core.dao.model.dto.Get.GetRankingDto;
import com.example.aftas.core.dao.model.dto.Store.RankingDto;
import com.example.aftas.core.dao.model.entity.Ranking;
import com.example.aftas.core.dao.model.entity.RankingKey;

import java.util.List;

public interface RankingService {
    RankingDto RegisterMemberInCompetition(RankingDto rankingDto);

    List<GetRankingDto> getAllRankings();
}
