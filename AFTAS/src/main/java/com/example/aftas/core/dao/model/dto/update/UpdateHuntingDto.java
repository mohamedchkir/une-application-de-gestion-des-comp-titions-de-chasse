package com.example.aftas.core.dao.model.dto.update;

import com.example.aftas.core.dao.model.dto.Store.CompetitionDto;
import com.example.aftas.core.dao.model.dto.Store.FishDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UpdateHuntingDto implements Serializable {
    @NotNull(message = "id cannot be null.")
    @Positive(message = "id cannot be negative.")
    private Integer id;

    @NotNull(message = "number of fish cannot be null.")
    @PositiveOrZero(message = "number of fish cannot be negative.")
    private Integer numberOfFish;

    @NotNull(message = "fish cannot be null.")
    private FishDto fish;

    @NotNull(message = "member cannot be null.")
    private UpdateMemberDto member;

    @NotNull(message = "competition cannot be null.")
    private CompetitionDto competition;
}