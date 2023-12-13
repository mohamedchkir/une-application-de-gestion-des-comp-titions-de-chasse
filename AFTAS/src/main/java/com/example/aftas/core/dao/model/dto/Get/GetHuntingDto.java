package com.example.aftas.core.dao.model.dto.Get;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetHuntingDto implements Serializable {
    private Integer id;
    private Integer numberOfFish;
    private GetFishDto fish;
    private GetMemberDto member;
    private GetCompetitionDto competition;
}