package com.example.aftas.core.dao.repository;

import com.example.aftas.core.dao.model.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, String> {
    List<Competition> findByDate(LocalDate date);
}
