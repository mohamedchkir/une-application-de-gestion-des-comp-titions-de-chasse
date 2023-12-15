package com.example.aftas.core.dao.repository;

import com.example.aftas.core.dao.model.entity.Competition;
import com.example.aftas.core.dao.model.entity.Hunting;
import com.example.aftas.core.dao.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
    List<Hunting> findByCompetitionAndMember(Competition competition, Member member);
}
