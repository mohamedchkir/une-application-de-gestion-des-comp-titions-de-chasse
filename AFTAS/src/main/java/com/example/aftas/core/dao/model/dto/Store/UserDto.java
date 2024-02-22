package com.example.aftas.core.dao.model.dto.Store;


import com.example.aftas.shared.Enum.IdentityDocument;
import com.example.aftas.shared.Enum.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {
    @NotNull(message = "num cannot be null.")
    @Positive(message = "num cannot be negative.")
    private Integer num;

    @NotNull(message = "firstName cannot be null.")
    @NotBlank(message = "firstName cannot be blank.")
    private String firstName;

    @NotNull(message = "last name cannot be null.")
    @NotBlank(message = "last name cannot be blank.")
    private String lastName;

    @NotNull(message = "nationality cannot be null.")
    @NotBlank(message = "nationality cannot be blank.")
    private String nationality;

    @NotBlank(message = "identity number cannot be blank.")
    private String identityNumber;
    private IdentityDocument identityDocument;
    private Role role;
}