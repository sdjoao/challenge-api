package com.project.challenge.dto;

import com.project.challenge.enums.ChallengeDifficulty;

public record ChallengeRequestDTO(
    String title,
    String description,
    ChallengeDifficulty difficulty,
    float score
){}
