package com.example.aftas.core.service;

import com.example.aftas.core.dao.model.entity.Level;

import java.util.List;

public interface LevelService {
    Level addLevel(Level level);

    List<Level> getAllLevels();

    Level getLevelByCode(Integer code);

}
