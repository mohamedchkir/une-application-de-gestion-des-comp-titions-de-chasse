package com.example.aftas.core.service;

import com.example.aftas.core.dao.model.dto.Get.GetFishDto;
import com.example.aftas.core.dao.model.dto.Store.FishDto;
import com.example.aftas.core.dao.model.entity.Fish;

import java.util.List;

public interface FishService {
    FishDto addFish(FishDto fish);

    List<GetFishDto> getAllFish();

    GetFishDto getFishByName(String name);
}
