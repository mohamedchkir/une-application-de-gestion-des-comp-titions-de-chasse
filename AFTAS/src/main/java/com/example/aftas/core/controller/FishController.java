package com.example.aftas.core.controller;

import com.example.aftas.core.dao.model.dto.Get.GetFishDto;
import com.example.aftas.core.dao.model.dto.Store.FishDto;
import com.example.aftas.core.service.FishService;
import com.example.aftas.shared.Const.AppEndpoints;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(AppEndpoints.FISH_ENDPOINT)
public class FishController {
    public final FishService fishService;

    @PostMapping
    public ResponseEntity<GetFishDto> addFish(@RequestBody @Valid FishDto fishDto) {
        try {
            GetFishDto createdFish = fishService.addFish(fishDto);
            return new ResponseEntity<>(createdFish, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<GetFishDto>> getAllFish() {
        List<GetFishDto> allFish = fishService.getAllFish();
        return new ResponseEntity<>(allFish, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<GetFishDto> getFishByName(@PathVariable String name) {
        try {
            GetFishDto fish = fishService.getFishByName(name);
            return new ResponseEntity<>(fish, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
