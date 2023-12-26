package com.example.aftas.core.service;

import com.example.aftas.core.dao.model.dto.Get.GetHuntingDto;
import com.example.aftas.core.dao.model.dto.Store.HuntingDto;
import com.example.aftas.core.dao.model.entity.Hunting;

import java.util.List;

public interface HuntingService {
    GetHuntingDto addHunting(HuntingDto huntingDto);

    List<GetHuntingDto> findByCompetition(String code);
}
