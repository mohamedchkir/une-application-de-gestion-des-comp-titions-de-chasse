package com.example.aftas.common.helper.Validation.Compitition;

import com.example.aftas.core.dao.model.dto.Store.CompetitionDto;
import com.example.aftas.core.dao.model.entity.Competition;
import com.example.aftas.shared.Enum.CompetitionStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Competition duration must be greater than 2 hours");
        }
    }

    private void validateExistingCompetition(LocalDate competitionDate, List<Competition> existingCompetitions) {
        for (Competition existingCompetition : existingCompetitions) {
            if (existingCompetition.getDate().isEqual(competitionDate)) {
                throw new ResponseStatusException(HttpStatus.FOUND,"There is already a competition on the same day");
            }
        }
    }

    public static CompetitionStatus calculateCompetitionStatus(Competition competition) {
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
