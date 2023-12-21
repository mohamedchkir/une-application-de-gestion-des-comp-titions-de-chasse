package com.example.aftas.core.service.impl;

import com.example.aftas.common.helper.Validation.Compitition.CompetitionCodeGenerator;
import com.example.aftas.common.helper.Validation.Compitition.CompetitionValidation;
import com.example.aftas.core.dao.model.dto.Get.GetCompetitionDto;
import com.example.aftas.core.dao.model.dto.Get.GetRankingDto;
import com.example.aftas.core.dao.model.dto.Store.CompetitionDto;
import com.example.aftas.core.dao.model.entity.Competition;
import com.example.aftas.core.dao.model.entity.Hunting;
import com.example.aftas.core.dao.model.entity.Ranking;
import com.example.aftas.core.dao.repository.CompetitionRepository;
import com.example.aftas.core.dao.repository.HuntingRepository;
import com.example.aftas.core.dao.repository.MemberRepository;
import com.example.aftas.core.dao.repository.RankingRepository;
import com.example.aftas.core.service.CompetitionService;
import com.example.aftas.shared.Enum.CompetitionStatus;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final CompetitionValidation competitionValidation;
    private final RankingRepository rankingRepository;
    private final MemberRepository memberRepository;
    private HuntingRepository huntingRepository;
    private final ModelMapper modelMapper;

    @Override
    public CompetitionDto addCompetition(CompetitionDto competitionDto) {

        List<Competition> competitionsOnSameDay = competitionRepository.findByDate(competitionDto.getDate());
        // Validate competition using the validation service
        competitionValidation.validateCompetition(competitionDto, competitionsOnSameDay);

        String competitionCode = CompetitionCodeGenerator.generateCode(competitionDto);

        Competition competition = modelMapper.map(competitionDto, Competition.class);
        competition.setCode(competitionCode);
        competition.setNumberOfParticipants(0);
        Competition savedCompetition = competitionRepository.save(competition);

        return modelMapper.map(savedCompetition, CompetitionDto.class);
    }

    @Override
    public Page<GetCompetitionDto> getAllCompetitionsWithStatus(int page, int size) {
        Page<Competition> competitions = competitionRepository.findAll(PageRequest.of(page, size));
        return competitions.map(this::mapCompetitionToDtoWithStatus);
    }

    @Override
    public List<GetRankingDto> calculateScore(String code) {
        Optional<Competition> optionalCompetition = competitionRepository.findById(code);
        if (optionalCompetition.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no competition with that code.");
        }

        List<Ranking> rankings = rankingRepository.findByCompetition(optionalCompetition.get());

        List<Ranking> rankingsAfterCalcScore = rankings
                .stream()
                .peek(ranking -> {
                    List<Hunting> userHunting = huntingRepository.findByCompetitionAndMember(ranking.getCompetition(), ranking.getMember());
                    Integer huntScore = userHunting
                            .stream()
                            .map(hunting -> hunting.getFish().getLevel().getPoints() * hunting.getNumberOfFish())
                            .mapToInt(Integer::intValue)
                            .sum();

                    ranking.setScore(huntScore);
                })
                .sorted(Comparator.comparingInt(Ranking::getScore).reversed())
                .toList();
        List<Ranking> savedRankings = rankingsAfterCalcScore.stream().peek(ranking -> ranking.setRank(rankingsAfterCalcScore.indexOf(ranking) + 1)).toList();

        rankingRepository.saveAll(rankings);

        return savedRankings
                .stream()
                .map(element -> GetRankingDto.builder().rank(element.getRank()).score(element.getScore()).build())
                .collect(Collectors.toList());
    }

    private GetCompetitionDto mapCompetitionToDtoWithStatus(Competition competition) {
        GetCompetitionDto competitionDto = modelMapper.map(competition, GetCompetitionDto.class);
        competitionDto.setStatus(CompetitionValidation.calculateCompetitionStatus(competition));
        return competitionDto;
    }

}
