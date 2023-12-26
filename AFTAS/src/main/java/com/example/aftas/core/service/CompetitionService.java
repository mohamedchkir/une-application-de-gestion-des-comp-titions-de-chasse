package com.example.aftas.core.service;

import com.example.aftas.core.dao.model.dto.Get.GetCompetitionDto;
import com.example.aftas.core.dao.model.dto.Get.GetRankingDto;
import com.example.aftas.core.dao.model.dto.Store.CompetitionDto;
import com.example.aftas.core.dao.model.entity.Competition;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CompetitionService {
    CompetitionDto addCompetition(CompetitionDto competitionDto);
    Page<GetCompetitionDto> getAllCompetitionsWithStatus(int page, int size);
    List<GetRankingDto> calculateScore(String competitionCode);
    GetCompetitionDto getCompetitionByCode(String competitionCode);
}
