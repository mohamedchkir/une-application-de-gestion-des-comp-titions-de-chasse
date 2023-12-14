package com.example.aftas.core.controller;

import com.example.aftas.common.helper.Validation.Compitition.CompetitionValidation;
import com.example.aftas.core.dao.model.dto.Store.LevelDto;
import com.example.aftas.core.dao.repository.CompetitionRepository;
import com.example.aftas.core.service.CompetitionService;
import com.example.aftas.core.service.LevelService;
import com.example.aftas.shared.Const.AppEndpoints;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(AppEndpoints.LEVEL_ENDPOINT)
public class LevelController {
    private final LevelService levelService;

    @PostMapping
    public ResponseEntity<LevelDto> addLevel(@RequestBody @Valid LevelDto levelDto) {
        LevelDto addedLevel = levelService.addLevel(levelDto);
        return new ResponseEntity<>(addedLevel, HttpStatus.CREATED);
    }
}
