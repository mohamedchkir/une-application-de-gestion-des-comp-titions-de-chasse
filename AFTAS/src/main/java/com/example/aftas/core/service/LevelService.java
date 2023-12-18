package com.example.aftas.core.service;

import com.example.aftas.core.dao.model.dto.Get.GetLevelDto;
import com.example.aftas.core.dao.model.dto.Store.LevelDto;

import java.util.List;

public interface LevelService {
    GetLevelDto addLevel(LevelDto levelDto);

    List<GetLevelDto> getAllLevels();

    GetLevelDto getLevelByCode(Integer code);

}
