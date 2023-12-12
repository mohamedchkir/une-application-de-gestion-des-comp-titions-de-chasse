package com.example.aftas.core.dao.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public record LevelDto(
        @NotNull(message = "points cannot be null.") @Positive(message = "points cannot be negative.") Integer points,
        @NotBlank(message = "description cannot be blank.") String description) implements Serializable {
}