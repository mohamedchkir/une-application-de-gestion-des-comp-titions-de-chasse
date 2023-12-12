package com.example.aftas.core.dao.repository;

import com.example.aftas.core.dao.model.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, String> {
}
