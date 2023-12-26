package com.example.aftas.core.dao.model.dto.Get;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetRankingDto {
    private Integer rank;
    private Integer score;
    private GetCompetitionDto competition;
    private GetMemberDto member;
}