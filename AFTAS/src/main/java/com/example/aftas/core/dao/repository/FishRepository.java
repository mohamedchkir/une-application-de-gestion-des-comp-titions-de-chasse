package com.example.aftas.core.dao.repository;

import com.example.aftas.core.dao.model.entity.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, String> {
}
