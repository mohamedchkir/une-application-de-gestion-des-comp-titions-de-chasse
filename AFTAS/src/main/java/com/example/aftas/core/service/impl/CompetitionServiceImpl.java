package com.example.aftas.core.service.impl;

import com.example.aftas.core.dao.model.entity.Competition;
import com.example.aftas.core.dao.repository.CompetitionRepository;
import com.example.aftas.core.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Competition addCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }
    
    @Override
    public Competition getCompetitionByCode(String code) {
        return competitionRepository.findById(code).orElse(null);
    }


}
