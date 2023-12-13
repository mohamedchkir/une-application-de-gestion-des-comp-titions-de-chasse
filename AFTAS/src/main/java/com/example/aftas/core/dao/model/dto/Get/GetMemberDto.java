package com.example.aftas.core.dao.model.dto.Get;

import com.example.aftas.shared.Enum.IdentityDocument;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class GetMemberDto implements Serializable {
    private Integer num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    private String identityNumber;
    private IdentityDocument identityDocument;
}