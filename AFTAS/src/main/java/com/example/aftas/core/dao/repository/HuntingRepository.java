package com.example.aftas.core.dao.repository;

import com.example.aftas.core.dao.model.dto.Get.GetHuntingDto;
import com.example.aftas.core.dao.model.entity.Competition;
import com.example.aftas.core.dao.model.entity.Fish;
import com.example.aftas.core.dao.model.entity.Hunting;
import com.example.aftas.core.dao.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
    List<Hunting> findByCompetitionAndMember(Competition competition, Member member);

    Optional<Hunting> findByFishAndCompetitionAndMember(Fish fish, Competition competition, Member member);

    List<Hunting> findByCompetition(Competition competition);
}
