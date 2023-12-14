package com.example.aftas.core.dao.repository;

import com.example.aftas.core.dao.model.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
    Optional<Level> findTopByOrderByPointsDesc();
}
