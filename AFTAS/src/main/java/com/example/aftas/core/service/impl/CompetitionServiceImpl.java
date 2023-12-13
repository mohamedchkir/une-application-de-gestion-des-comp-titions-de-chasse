package com.example.aftas.core.service.impl;

import com.example.aftas.common.helper.Validation.Compitition.CompetitionCodeGenerator;
import com.example.aftas.common.helper.Validation.Compitition.CompetitionValidation;
import com.example.aftas.core.dao.model.dto.Get.GetCompetitionDto;
import com.example.aftas.core.dao.model.dto.Store.CompetitionDto;
import com.example.aftas.core.dao.model.entity.Competition;
import com.example.aftas.core.dao.repository.CompetitionRepository;
import com.example.aftas.core.service.CompetitionService;
import com.example.aftas.shared.Enum.CompetitionStatus;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<GetCompetitionDto> getAllCompetitionsWithStatus() {
        List<Competition> competitions = competitionRepository.findAll();
        return competitions.stream()
                .map(this::mapCompetitionToDtoWithStatus)
                .collect(Collectors.toList());
    }

    @Override
    public Competition getCompetitionByCode(String code) {
        return null;
    }

    private GetCompetitionDto mapCompetitionToDtoWithStatus(Competition competition) {
        GetCompetitionDto competitionDto = modelMapper.map(competition, GetCompetitionDto.class);
        competitionDto.setStatus(calculateCompetitionStatus(competition));
        return competitionDto;
    }

    private CompetitionStatus calculateCompetitionStatus(Competition competition) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate competitionDate = competition.getDate();

        if (currentDateTime.isBefore(competitionDate.atTime(competition.getStartTime()))) {
            return CompetitionStatus.UPCOMING;
        } else if (currentDateTime.isAfter(competitionDate.atTime(competition.getEndTime()))) {
            return CompetitionStatus.COMPLETED;
        } else {
            return CompetitionStatus.ONGOING;
        }
    }


}
