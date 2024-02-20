package com.example.aftas.core.service.impl;

import com.example.aftas.core.dao.model.dto.Get.GetCompetitionDto;
import com.example.aftas.core.dao.model.dto.Get.GetHuntingDto;
import com.example.aftas.core.dao.model.dto.Store.HuntingDto;
import com.example.aftas.core.dao.model.entity.*;
import com.example.aftas.core.dao.repository.*;
import com.example.aftas.core.service.CompetitionService;
import com.example.aftas.core.service.HuntingService;
import com.example.aftas.shared.Enum.CompetitionStatus;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class HuntingServiceImpl implements HuntingService {
    private final HuntingRepository repository;
    private final CompetitionRepository competitionRepository;
    private final MemberRepository memberRepository;
    private final FishRepository fishRepository;
    private final RankingRepository rankingRepository;
    private final ModelMapper mapper;
    private final CompetitionService competitionService;

    @Override
    public GetHuntingDto addHunting(HuntingDto huntingDto) {
        Optional<Competition> optionalCompetition = competitionRepository.findById(huntingDto.getCompetition().getCode());
        if (optionalCompetition.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "their is no competition with that code.");
        }

        Optional<User> optionalMember = memberRepository.findById(huntingDto.getMember().getNum());
        if (optionalMember.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "their is no member with that number.");
        }

        Optional<Fish> fishOptional = fishRepository.findById(huntingDto.getFish().getName());
        if (fishOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "their is no fish with that name.");
        }

        if (fishOptional.get().getAverageWeight() > huntingDto.getFishWeight()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "weight to small, the average weight for " + fishOptional.get().getName() + ", is " + fishOptional.get().getAverageWeight());
        }

        Hunting hunting = mapper.map(huntingDto, Hunting.class);

        Optional<Ranking> optionalRanking = rankingRepository.findById(new RankingKey(hunting.getCompetition().getCode(), hunting.getUser().getNum()));
        if (optionalRanking.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "you are not registered to this competition.");
        }

        Optional<GetCompetitionDto> getCompetitionDto = Optional.ofNullable(competitionService.getCompetitionByCode(hunting.getCompetition().getCode()));
        if (!(getCompetitionDto.isPresent() && getCompetitionDto.get().getStatus().equals(CompetitionStatus.ONGOING))) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "you can store a hunt only when competition is ongoing.");
        }

        Optional<Hunting> existsHunting = repository.findByFishAndCompetitionAndMember(hunting.getFish(), hunting.getCompetition(), hunting.getUser());
        if (existsHunting.isPresent()) {
            Hunting hunt = existsHunting.get();
            hunt.setNumberOfFish(hunt.getNumberOfFish() + 1);
            Hunting merged = repository.save(hunt);
            return mapper.map(merged, GetHuntingDto.class);
        } else {
            hunting.setNumberOfFish(1);
            Hunting saved = repository.save(hunting);
            return mapper.map(saved, GetHuntingDto.class);
        }
    }

    @Override
    public List<GetHuntingDto> findByCompetition(String code) {

        Optional<Competition> optionalCompetition = competitionRepository.findById(code);
        if (optionalCompetition.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "their is no competition with that code.");
        }

        return repository
                .findByCompetition(optionalCompetition.get())
                .stream()
                .map((element) -> mapper.map(element, GetHuntingDto.class))
                .collect(Collectors.toList());
    }
}

