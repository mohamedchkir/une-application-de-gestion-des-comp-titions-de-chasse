package com.example.aftas.common.helper.Validation.Compitition;

import com.example.aftas.core.dao.model.dto.Store.CompetitionDto;
import com.example.aftas.core.dao.model.entity.Competition;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class CompetitionValidation {
    public void validateCompetition(CompetitionDto competitionDto, List<Competition> existingCompetitions) {
        validateDuration(competitionDto.getStartTime(), competitionDto.getEndTime());
        validateExistingCompetition(competitionDto.getDate(), existingCompetitions);
    }

    private void validateDuration(LocalTime startTime, LocalTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        if (duration.toHours() <= 2) {
            throw new IllegalArgumentException("Competition duration must be greater than 2 hours");
        }
    }

    private void validateExistingCompetition(LocalDate competitionDate, List<Competition> existingCompetitions) {
        for (Competition existingCompetition : existingCompetitions) {
            if (existingCompetition.getDate().isEqual(competitionDate)) {
                throw new IllegalArgumentException("There is already a competition on the same day");
            }
        }
    }
}
