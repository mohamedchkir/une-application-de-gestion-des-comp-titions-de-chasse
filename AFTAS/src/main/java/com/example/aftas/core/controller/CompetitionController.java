package com.example.aftas.core.controller;

import com.example.aftas.core.dao.model.dto.Get.GetCompetitionDto;
import com.example.aftas.core.dao.model.dto.Store.CompetitionDto;
import com.example.aftas.core.dao.model.entity.Competition;
import com.example.aftas.core.service.CompetitionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CompetitionDto> addCompetition( @Valid @RequestBody CompetitionDto competitionDto) {
        try {
            CompetitionDto createdCompetition = competitionService.addCompetition(competitionDto);
            return new ResponseEntity<>(createdCompetition, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
