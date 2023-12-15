package com.example.aftas.core.service.impl;

import com.example.aftas.core.dao.model.dto.Get.GetFishDto;
import com.example.aftas.core.dao.model.dto.Store.FishDto;
import com.example.aftas.core.dao.model.entity.Fish;
import com.example.aftas.core.dao.model.entity.Level;
import com.example.aftas.core.dao.repository.FishRepository;
import com.example.aftas.core.dao.repository.LevelRepository;
import com.example.aftas.core.service.FishService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FishServiceImpl implements FishService {
    private final FishRepository fishRepository;
    private final ModelMapper modelMapper;
    private final LevelRepository levelRepository;


    @Override
    public FishDto addFish(FishDto fishDto) {
        Level level = levelRepository.findById(fishDto.getLevel().getCode())
                .orElseThrow(() -> new IllegalArgumentException("Level not found"));

        if (fishRepository.existsByName(fishDto.getName())) {
            throw new IllegalArgumentException("A fish with the same name already exists");
        }

        Fish fish = modelMapper.map(fishDto, Fish.class);
        fish.setLevel(level);

        Fish savedFish = fishRepository.save(fish);

        return modelMapper.map(savedFish, FishDto.class);
    }

    @Override
    public List<GetFishDto> getAllFish() {
        return null;
    }

    @Override
    public GetFishDto getFishByName(String name) {
        return null;
    }
}

