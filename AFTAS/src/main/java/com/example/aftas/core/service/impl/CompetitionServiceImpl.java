package com.example.aftas.core.service.impl;

import com.example.aftas.common.helper.Validation.Compitition.CompetitionCodeGenerator;
import com.example.aftas.common.helper.Validation.Compitition.CompetitionValidation;
import com.example.aftas.core.dao.model.dto.Store.CompetitionDto;
import com.example.aftas.core.dao.model.entity.Competition;
import com.example.aftas.core.dao.repository.CompetitionRepository;
import com.example.aftas.core.service.CompetitionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final CompetitionValidation competitionValidation;
    private final ModelMapper modelMapper;

    @Override
    public CompetitionDto addCompetition(CompetitionDto competitionDto) {

        List<Competition> competitionsOnSameDay = competitionRepository.findByDate(competitionDto.getDate());
        // Validate competition using the validation service
        competitionValidation.validateCompetition(competitionDto, competitionsOnSameDay);

        String competitionCode = CompetitionCodeGenerator.generateCode(competitionDto);

        Competition competition = modelMapper.map(competitionDto, Competition.class);
        competition.setCode(competitionCode);

        Competition savedCompetition = competitionRepository.save(competition);

        return modelMapper.map(savedCompetition, CompetitionDto.class);
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return null;
    }

    @Override
    public Competition getCompetitionByCode(String code) {
        return null;
    }



}
