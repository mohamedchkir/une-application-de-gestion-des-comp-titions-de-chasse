package com.example.aftas.core.dao.model.dto.Store;

import com.example.aftas.core.dao.model.dto.update.UpdateLevelDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class FishDto implements Serializable {
    @NotNull(message = "name cannot be null.")
    @NotBlank(message = "name cannot be blank.")
    private String name;

    @NotNull(message = "average weight cannot be null.")
    @Positive(message = "average weight cannot be negative.")
    private Double averageWeight;

    @NotNull(message = "level cannot be null.")
    private UpdateLevelDto level;
}