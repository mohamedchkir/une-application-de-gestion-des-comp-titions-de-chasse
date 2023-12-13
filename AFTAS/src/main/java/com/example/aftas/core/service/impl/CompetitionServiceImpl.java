package com.example.aftas.core.service.impl;

import com.example.aftas.core.dao.model.dto.Store.CompetitionDto;
import com.example.aftas.core.dao.model.entity.Competition;
import com.example.aftas.core.dao.repository.CompetitionRepository;
import com.example.aftas.core.service.CompetitionService;
import com.example.aftas.shared.Config.Beans;
import com.example.aftas.shared.Enum.CompetitionStatus;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;

    @Override
    public CompetitionDto addCompetition(CompetitionDto competitionDto) {
        validateDuration(competitionDto.getStartTime(), competitionDto.getEndTime());

        String competitionCode = generateCompetitionCode(competitionDto);

        checkExistingCompetition(competitionDto.getDate());

        Competition competition = modelMapper.map(competitionDto, Competition.class);
        competition.setCode(competitionCode);

        // Save the competition to the repository
        Competition savedCompetition = competitionRepository.save(competition);

        // Map the saved competition back to DTO
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

    private void validateDuration(LocalTime startTime, LocalTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        if (duration.toHours() <= 2) {
            throw new IllegalArgumentException("Competition duration must be greater than 2 hours");
        }
    }

    private String generateCompetitionCode(CompetitionDto competitionDto) {
        String location = competitionDto.getLocation();

        String locationDigits = location.substring(0, Math.min(location.length(), 3));

        return locationDigits + "-" + competitionDto.getDate().toString();
    }


    private void checkExistingCompetition(LocalDate competitionDate) {
        List<Competition> competitionsOnSameDay = competitionRepository.findByDate(competitionDate);
        if (!competitionsOnSameDay.isEmpty()) {
            throw new IllegalArgumentException("There is already a competition on the same day");
        }
    }

}
