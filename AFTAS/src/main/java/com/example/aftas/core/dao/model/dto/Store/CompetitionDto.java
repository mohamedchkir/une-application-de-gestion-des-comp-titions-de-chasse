package com.example.aftas.core.dao.model.dto.Store;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CompetitionDto implements Serializable {

        private String code;
        private Integer numberOfParticipants;

        @NotNull(message = "date cannot be null.")
        @Future(message = "date cannot be in the past.")
        private LocalDate date;

        @NotNull(message = "start time cannot be null.")
        private LocalTime startTime;

        @NotNull(message = "end time cannot be null.")
        private LocalTime endTime;

        @NotNull(message = "location cannot be null.")
        @NotBlank(message = "location cannot be blank.")
        private String location;

        @NotNull(message = "amount cannot be null.")
        @PositiveOrZero(message = "amount cannot be negative.")
        private Double amount;
}