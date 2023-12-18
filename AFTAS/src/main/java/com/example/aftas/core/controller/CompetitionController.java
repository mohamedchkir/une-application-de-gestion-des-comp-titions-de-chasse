package com.example.aftas.core.controller;

import com.example.aftas.common.helper.Validation.Compitition.CompetitionValidation;
import com.example.aftas.core.dao.model.dto.Get.GetCompetitionDto;
import com.example.aftas.core.dao.model.dto.Get.GetRankingDto;
import com.example.aftas.core.dao.model.dto.Store.CompetitionDto;
import com.example.aftas.core.dao.model.dto.Store.RankingDto;
import com.example.aftas.core.dao.model.entity.Competition;
import com.example.aftas.core.dao.repository.CompetitionRepository;
import com.example.aftas.core.service.CompetitionService;
import com.example.aftas.shared.Const.AppEndpoints;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(AppEndpoints.COMPETITION_ENDPOINT)
public class CompetitionController {

    private final CompetitionService competitionService;


    @PostMapping
    public ResponseEntity<Object> addCompetition(@Valid @RequestBody CompetitionDto competitionDto) {
        try {
            CompetitionDto createdCompetition = competitionService.addCompetition(competitionDto);
            throw new ResponseStatusException(HttpStatus.CREATED, "Competition created successfully");
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getReason());
        }
    }

    @GetMapping
    public ResponseEntity<List<GetCompetitionDto>> getAllCompetitionsWithStatus() {
        List<GetCompetitionDto> competitionDto = competitionService.getAllCompetitionsWithStatus();
        return ResponseEntity.ok(competitionDto);
    }
    @GetMapping("/{code}/calculate-score")
    public ResponseEntity<List<GetRankingDto>> calculateScore(@PathVariable String code) {
        List<GetRankingDto> calculatedScores = competitionService.calculateScore(code);
        return new ResponseEntity<>(calculatedScores, HttpStatus.OK);
    }



}
