package com.project.challenge.dto;

import com.project.challenge.enums.ChallengeDifficulty;

public record ChallengeResponseDTO(
    Long id,
    String title,
    String description,
    ChallengeDifficulty difficulty,
    float score,
    boolean active
){}
