package com.example.aftas.core.dao.repository;

import com.example.aftas.core.dao.model.entity.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
}
