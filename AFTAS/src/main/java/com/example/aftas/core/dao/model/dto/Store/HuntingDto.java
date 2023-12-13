package com.example.aftas.core.dao.model.dto.Store;

import com.example.aftas.core.dao.model.entity.Competition;
import com.example.aftas.core.dao.model.entity.Member;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record HuntingDto(
        @NotNull(message = "fish cannot be null.") FishDto fish,
        @NotNull(message = "member cannot be null.") Member member,
        @NotNull(message = "competition cannot be null.") Competition competition) implements Serializable {
}
