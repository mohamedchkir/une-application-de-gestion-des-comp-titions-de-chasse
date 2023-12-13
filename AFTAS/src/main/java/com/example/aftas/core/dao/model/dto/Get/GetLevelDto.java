package com.example.aftas.core.dao.model.dto.Get;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetLevelDto implements Serializable {
    private Integer code;
    private Integer points;
    private String description;
}