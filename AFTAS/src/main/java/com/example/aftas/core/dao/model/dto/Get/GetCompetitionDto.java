package com.example.aftas.core.dao.model.dto.Get;


import com.example.aftas.shared.Enum.CompetitionStatus;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCompetitionDto implements Serializable {
    private String code;
    private Integer numberOfParticipants;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private Double amount;
    private CompetitionStatus status;
}