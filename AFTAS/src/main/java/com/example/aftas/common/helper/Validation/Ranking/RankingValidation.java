package com.example.aftas.common.helper.Validation.Ranking;

import com.example.aftas.core.dao.model.entity.Competition;

import java.time.LocalDateTime;

public class RankingValidation {
    public static Boolean isRegisterBeforeTwentyFourHours(Competition competition) {
        LocalDateTime competitionDateTime = LocalDateTime.of(competition.getDate(), competition.getStartTime());
        LocalDateTime now = LocalDateTime.now();

        return now.isBefore(competitionDateTime.minusHours(24));
    }

}
