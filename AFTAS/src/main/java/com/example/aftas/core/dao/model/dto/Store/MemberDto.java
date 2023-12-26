package com.example.aftas.core.dao.model.dto.Store;


import com.example.aftas.shared.Enum.IdentityDocument;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MemberDto implements Serializable {
    @NotNull(message = "num cannot be null.")
    @Positive(message = "num cannot be negative.")
    private Integer num;

    @NotNull(message = "name cannot be null.")
    @NotBlank(message = "name cannot be blank.")
    private String name;

    @NotNull(message = "family name cannot be null.")
    @NotBlank(message = "family name cannot be blank.")
    private String familyName;

    @NotNull(message = "nationality cannot be null.")
    @NotBlank(message = "nationality cannot be blank.")
    private String nationality;

    @NotBlank(message = "identity number cannot be blank.")
    private String identityNumber;

    private IdentityDocument identityDocument;
}