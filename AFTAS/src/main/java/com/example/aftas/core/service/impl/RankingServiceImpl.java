package com.example.aftas.core.service.impl;

import com.example.aftas.common.helper.Validation.Ranking.RankingValidation;
import com.example.aftas.core.dao.model.dto.Get.GetRankingDto;
import com.example.aftas.core.dao.model.dto.Store.RankingDto;
import com.example.aftas.core.dao.model.entity.*;
import com.example.aftas.core.dao.repository.CompetitionRepository;
import com.example.aftas.core.dao.repository.HuntingRepository;
import com.example.aftas.core.dao.repository.UserRepository;
import com.example.aftas.core.dao.repository.RankingRepository;
import com.example.aftas.core.service.RankingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private HuntingRepository huntingRepository;

    @Override
    public RankingDto RegisterMemberInCompetition(RankingDto rankingDto) {
        // Check if the competition exists
        Competition competition = competitionRepository.findById(rankingDto.getCompetition().getCode())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no competition with that code."));

        // Check if the member exists
        User user = userRepository.findByNum(rankingDto.getMember().getNum())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no member with that number."));

        // Create the ranking key and check if the member is already registered in the competition
        RankingKey rankingKey = new RankingKey(rankingDto.getCompetition().getCode(), rankingDto.getMember().getNum());
        if (rankingRepository.findById(rankingKey).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This member is already registered in that competition.");
        }

        // Check if the registration is within 24 hours before the competition
        if (!RankingValidation.isRegisterBeforeTwentyFourHours(competition)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"You can't register now, the registration closes before 24 hours.");
        }

        // Save the ranking
        Ranking ranking = modelMapper.map(rankingDto, Ranking.class);
        ranking.setId(rankingKey);
        Ranking saved = rankingRepository.save(ranking);

        //increment the number of participants in the competition
        competition.setNumberOfParticipants(competition.getNumberOfParticipants() + 1);
        Competition savedCompetition = competitionRepository.save(competition);

        return modelMapper.map(saved, RankingDto.class);
    }

    @Override
    public List<GetRankingDto> getAllRankings() {

        return rankingRepository.findAll().stream()
                .map((element) -> modelMapper.map(element, GetRankingDto.class))
                .collect(Collectors.toList());
    }


}
