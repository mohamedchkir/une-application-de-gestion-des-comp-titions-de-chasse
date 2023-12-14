package com.example.aftas.core.service;

import com.example.aftas.core.dao.model.dto.Get.GetCompetitionDto;
import com.example.aftas.core.dao.model.dto.Store.CompetitionDto;
import com.example.aftas.core.dao.model.entity.Competition;

import java.util.List;

public interface CompetitionService {
    CompetitionDto addCompetition(CompetitionDto competitionDto);

    List<GetCompetitionDto> getAllCompetitionsWithStatus();

}
