package com.example.aftas.core.controller;

import com.example.aftas.core.dao.model.dto.Get.GetRankingDto;
import com.example.aftas.core.dao.model.dto.Store.RankingDto;
import com.example.aftas.core.service.RankingService;
import com.example.aftas.shared.Const.AppEndpoints;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(AppEndpoints.RANKING_ENDPOINT)
public class RankingController {

    private final RankingService rankingService;

    @PostMapping("/register")
    public ResponseEntity<RankingDto> registerMemberInCompetition(@RequestBody @Valid RankingDto rankingDto) {
        try {
            RankingDto registeredRanking = rankingService.RegisterMemberInCompetition(rankingDto);
            return new ResponseEntity<>(registeredRanking, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getReason());
            }
        }
    @GetMapping
    public ResponseEntity<List<GetRankingDto>> getAllRankings() {
        List<GetRankingDto> rankings = rankingService.getAllRankings();
        return new ResponseEntity<>(rankings, HttpStatus.OK);
    }
    }