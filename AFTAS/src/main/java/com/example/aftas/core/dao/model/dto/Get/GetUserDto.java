package com.example.aftas.core.dao.model.dto.Get;

import com.example.aftas.shared.Enum.IdentityDocument;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserDto implements Serializable {
    private Integer num;
    private String firstName;
    private String lastName;
    private LocalDate accessionDate;
    private String email;
    private String role;
    private List<String> permissions;
    private String nationality;
    private String identityNumber;
    private IdentityDocument identityDocument;
}