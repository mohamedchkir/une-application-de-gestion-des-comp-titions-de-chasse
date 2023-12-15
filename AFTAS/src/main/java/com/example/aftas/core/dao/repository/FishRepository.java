package com.example.aftas.core.dao.repository;

import com.example.aftas.core.dao.model.entity.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FishRepository extends JpaRepository<Fish, String> {
    boolean existsByName(String name);

    Optional<Fish> findByName(String name);
}
