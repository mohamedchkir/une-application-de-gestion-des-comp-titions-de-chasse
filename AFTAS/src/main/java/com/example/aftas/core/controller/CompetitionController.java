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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('copetition:write')")
    public ResponseEntity<Object> addCompetition(@Valid @RequestBody CompetitionDto competitionDto) {
        try {
            CompetitionDto createdCompetition = competitionService.addCompetition(competitionDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCompetition);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getReason());
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('competition:read')")
    public ResponseEntity<Page<GetCompetitionDto>> getAllCompetitionsWithStatus(int page, int size) {
        Page<GetCompetitionDto> competitionDto = competitionService.getAllCompetitionsWithStatus(page, size);
        return ResponseEntity.ok(competitionDto);
    }

    @GetMapping("/{code}")
    @PreAuthorize("hasAuthority('competition:read')")
    public GetCompetitionDto getCompetitionByCode(@PathVariable String code) {
        return competitionService.getCompetitionByCode(code);
    }

    @GetMapping("/{code}/calculate-score")
    @PreAuthorize("hasAuthority('competition:read')")
    public ResponseEntity<List<GetRankingDto>> calculateScore(@PathVariable String code) {
        List<GetRankingDto> calculatedScores = competitionService.calculateScore(code);
        return new ResponseEntity<>(calculatedScores, HttpStatus.OK);
    }



}
