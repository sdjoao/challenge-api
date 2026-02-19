package com.project.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.challenge.entity.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query("SELECT c FROM Challenge c WHERE c.active = true ORDER BY c.score DESC")
    List<Challenge> findChallengeActiveByScore();

    
}
