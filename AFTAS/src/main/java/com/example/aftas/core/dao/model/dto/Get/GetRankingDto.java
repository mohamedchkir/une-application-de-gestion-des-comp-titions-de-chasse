package com.example.aftas.core.dao.model.dto.Get;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRankingDto {
    private Integer rank;
    private Integer score;
    private GetCompetitionDto competition;
    private GetMemberDto member;
}