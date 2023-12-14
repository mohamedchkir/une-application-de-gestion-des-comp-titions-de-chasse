package com.example.aftas.core.dao.repository;

import com.example.aftas.core.dao.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByNum(Integer num);
}
