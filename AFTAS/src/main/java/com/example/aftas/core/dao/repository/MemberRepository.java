package com.example.aftas.core.dao.repository;

import com.example.aftas.core.dao.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
