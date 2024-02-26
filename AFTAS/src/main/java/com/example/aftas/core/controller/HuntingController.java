package com.example.aftas.core.controller;

import com.example.aftas.core.dao.model.dto.Get.GetFishDto;
import com.example.aftas.core.dao.model.dto.Get.GetHuntingDto;
import com.example.aftas.core.dao.model.dto.Store.HuntingDto;
import com.example.aftas.core.service.HuntingService;
import com.example.aftas.shared.Const.AppEndpoints;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(AppEndpoints.HUNTING_ENDPOINT)
public class HuntingController {
    public final HuntingService huntingService;
    @PostMapping
    @PreAuthorize("hasAuthority('hunting:write')")
    public GetHuntingDto addHunting(@RequestBody @Valid HuntingDto huntingDto) {
        return huntingService.addHunting(huntingDto);
    }
    @GetMapping("/{code}")
    @PreAuthorize("hasAuthority('hunting:read')")
    public ResponseEntity<List<GetHuntingDto>> getHuntingByCompetition(@PathVariable String code) {
        try {
            List<GetHuntingDto> hunting = huntingService.findByCompetition(code);
            return new ResponseEntity<>(hunting, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
