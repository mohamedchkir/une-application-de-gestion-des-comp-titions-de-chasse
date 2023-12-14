package com.example.aftas.core.controller;

import com.example.aftas.core.dao.model.dto.Get.GetLevelDto;
import com.example.aftas.core.dao.model.dto.Store.LevelDto;
import com.example.aftas.core.service.LevelService;
import com.example.aftas.shared.Const.AppEndpoints;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<GetLevelDto>> getAllLevels() {
        List<GetLevelDto> levels = levelService.getAllLevels();
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<GetLevelDto> getLevelByCode(@PathVariable Integer code) {
        GetLevelDto levelDto = levelService.getLevelByCode(code);
        return new ResponseEntity<>(levelDto, HttpStatus.OK);
    }
}
