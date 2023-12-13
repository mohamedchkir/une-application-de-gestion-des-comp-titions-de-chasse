package com.example.aftas.core.dao.model.dto.Get;
;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetFishDto implements Serializable {
    private String name;
    private Double averageWeight;
    private GetLevelDto level;
}