package com.example.aftas.core.dao.model.dto.Store;


import com.example.aftas.shared.Enum.IdentityDocument;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public record MemberDto(
        @NotNull(message = "num cannot be null.") @Positive(message = "num cannot be negative.") Integer num,
        @NotNull(message = "name cannot be null.") @NotBlank(message = "name cannot be blank.") String name,
        @NotNull(message = "family name cannot be null.") @NotBlank(message = "family name cannot be blank.") String familyName,
        @NotNull(message = "nationality cannot be null.") @NotBlank(message = "nationality cannot be blank.") String nationality,
        @NotBlank(message = "identity number cannot be blank.") String identityNumber,
        IdentityDocument identityDocument) implements Serializable {
}