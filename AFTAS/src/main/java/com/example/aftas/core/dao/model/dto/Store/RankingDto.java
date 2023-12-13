package com.example.aftas.core.dao.model.dto.Store;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;

public record RankingDto(@NotNull(message = "id cannot be null.") RankingKeyDto id,
                              @NotNull(message = "rank cannot be null.") Integer rank,
                              @NotNull(message = "score cannot be null.") @PositiveOrZero(message = "score cannot be negative.") Integer score,
                              CompetitionDto competition, MemberDto member) implements Serializable {
    public record RankingKeyDto(
            @NotNull(message = "code cannot be null.") @NotBlank(message = "code cannot be blank.") String code,
            @NotNull(message = "num cannot be null.") @Positive(message = "num cannot be negative.") Integer num) implements Serializable {
    }
}