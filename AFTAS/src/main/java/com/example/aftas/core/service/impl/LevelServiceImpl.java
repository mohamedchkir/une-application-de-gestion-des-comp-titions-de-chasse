package com.example.aftas.core.service.impl;

import com.example.aftas.core.dao.model.dto.Get.GetLevelDto;
import com.example.aftas.core.dao.model.dto.Store.LevelDto;
import com.example.aftas.core.dao.model.entity.Level;
import com.example.aftas.core.dao.repository.LevelRepository;
import com.example.aftas.core.service.LevelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    @Override
    public LevelDto addLevel(LevelDto levelDto) {
        Optional<Level> maxLevelPoint = levelRepository.findTopByOrderByPointsDesc();

        maxLevelPoint.ifPresent(maxLevel -> {
            if (levelDto.getPoints() > maxLevel.getPoints()) {
                Level level = modelMapper.map(levelDto, Level.class);
                levelRepository.save(level);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Points must be greater than the previous level");
            }
        });

        return levelDto;
    }

    @Override
    public List<GetLevelDto> getAllLevels() {
        List<Level> levels = levelRepository.findAll();

        return levels.stream()
                .map(level -> modelMapper.map(level, GetLevelDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public GetLevelDto getLevelByCode(Integer code) {
        Optional<Level> optionalLevel = levelRepository.findByCode(code);

        if (optionalLevel.isPresent()) {
            return modelMapper.map(optionalLevel.get(), GetLevelDto.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "level with code " + code + " not found");
        }
    }

}
