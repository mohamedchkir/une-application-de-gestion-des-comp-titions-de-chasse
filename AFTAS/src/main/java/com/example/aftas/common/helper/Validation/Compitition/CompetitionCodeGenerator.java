package com.example.aftas.common.helper.Validation.Compitition;

import com.example.aftas.core.dao.model.dto.Store.CompetitionDto;

public class CompetitionCodeGenerator {
    public static String generateCode(CompetitionDto competitionDto) {
        String location = competitionDto.getLocation();
        String locationDigits = location.substring(0, Math.min(location.length(), 3)).toUpperCase();
        return locationDigits + "-" + competitionDto.getDate().toString();
    }
}
