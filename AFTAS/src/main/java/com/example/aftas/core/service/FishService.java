package com.example.aftas.core.service;

import com.example.aftas.core.dao.model.entity.Fish;

import java.util.List;

public interface FishService {
    Fish addFish(Fish fish);

    List<Fish> getAllFish();

    Fish getFishByName(String name);
}
