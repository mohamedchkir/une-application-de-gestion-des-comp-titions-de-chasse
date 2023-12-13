package com.example.aftas.core.dao.model.dto.Store;

import com.example.aftas.core.dao.model.dto.update.UpdateMemberDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RankingDto implements Serializable {
    @NotNull(message = "rank cannot be null.")
    private Integer rank;

    @NotNull(message = "score cannot be null.")
    @PositiveOrZero(message = "score cannot be negative.")
    private Integer score;

    @NotNull(message = "competition cannot be null.")
    private CompetitionDto competition;

    @NotNull(message = "member cannot be null.")
    private UpdateMemberDto member;
}