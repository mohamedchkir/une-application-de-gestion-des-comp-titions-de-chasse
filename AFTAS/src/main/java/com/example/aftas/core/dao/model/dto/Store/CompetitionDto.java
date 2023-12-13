package com.example.aftas.core.dao.model.dto.Store;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionDto(
        @NotNull(message = "code cannot be null.") @NotBlank(message = "code cannot be blank.") String code,
        @NotNull(message = "date cannot be null.") @Future(message = "date cannot be in past.") LocalDate date,
        @NotNull(message = "start time cannot be null.") @FutureOrPresent(message = "start time cannot be past.") LocalTime startTime,
        @NotNull(message = "end time cannot be null.") @Future(message = "end time cannot be in past.") LocalTime endTime,
        @NotNull(message = "location cannot be null.") @NotBlank(message = "location cannot be null.") String location,
        @NotNull(message = "amount cannot be null.") @PositiveOrZero(message = "amount cannot be negative.") Double amount) implements Serializable {
}