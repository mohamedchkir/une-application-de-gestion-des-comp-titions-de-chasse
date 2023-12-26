package com.example.aftas.core.dao.model.dto.Store;

import com.example.aftas.core.dao.model.dto.update.UpdateMemberDto;
import com.example.aftas.core.dao.model.entity.Competition;
import com.example.aftas.core.dao.model.entity.Member;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class HuntingDto implements Serializable {

    @NotNull(message = "fish weight cannot be null.")
    @Positive(message = "fish weight cannot be negative.")
    private Double fishWeight;

    @NotNull(message = "fish cannot be null.")
    private FishDto fish;

    @NotNull(message = "member cannot be null.")
    private UpdateMemberDto member;

    @NotNull(message = "competition cannot be null.")
    private CompetitionDto competition;
}
