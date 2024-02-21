package com.example.aftas.core.dao.repository;

import com.example.aftas.core.dao.model.entity.Competition;
import com.example.aftas.core.dao.model.entity.Fish;
import com.example.aftas.core.dao.model.entity.Hunting;
import com.example.aftas.core.dao.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
    List<Hunting> findByCompetitionAndUser(Competition competition, User user);

    Optional<Hunting> findByFishAndCompetitionAndUser(Fish fish, Competition competition, User user);

    List<Hunting> findByCompetition(Competition competition);
}
