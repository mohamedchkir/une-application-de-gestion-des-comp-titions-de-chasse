package com.example.aftas.core.service;

import com.example.aftas.core.dao.model.entity.Competition;

import java.util.List;

public interface CompetitionService {
    Competition addCompetition(Competition competition);

    List<Competition> getAllCompetitions();

    List<Competition> getOpenCompetitions();

    Competition getCompetitionByCode(String code);
}
