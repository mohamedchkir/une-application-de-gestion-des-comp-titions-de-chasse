package com.example.aftas.core.dao.repository;

import com.example.aftas.core.dao.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query("select t from Token t where t.user.num = :userId and t.expired = false and t.revoked = false")
    List<Token> findAllValidByUserId(Integer userId);

    Optional<Token> findByToken(String token);
}
