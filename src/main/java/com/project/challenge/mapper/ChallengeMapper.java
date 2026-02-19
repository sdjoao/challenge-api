package com.project.challenge.mapper;

import java.util.ArrayList;
import java.util.List;

import com.project.challenge.dto.ChallengeResponseDTO;
import com.project.challenge.entity.Challenge;

public class ChallengeMapper {
    
    public static ChallengeResponseDTO conversor(Challenge challenge){
        return new ChallengeResponseDTO(
            challenge.getId(),
            challenge.getTitle(),
            challenge.getDescription(),
            challenge.getDifficulty(),
            challenge.getScore(),
            challenge.isActive()
        );
    }

    public static List<ChallengeResponseDTO> listConversor(List<Challenge> challenges){
        return challenges
            .stream()
            .map(challenge -> new ChallengeResponseDTO(
                challenge.getId(),
                challenge.getTitle(),
                challenge.getDescription(),
                challenge.getDifficulty(),
                challenge.getScore(),
                challenge.isActive()
            ))
            .toList();
    }
}
