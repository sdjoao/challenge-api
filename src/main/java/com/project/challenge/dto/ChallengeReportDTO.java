package com.project.challenge.dto;

// classe para gerar relatórios
public class ChallengeReportDTO {
    private Long id;
    private String title;
    private String difficulty;
    private float score;
    
    public ChallengeReportDTO(Long id, String title, String difficulty, float score) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public float getScore() {
        return score;
    }

    
}
