package com.project.challenge.entity;

import com.project.challenge.enums.ChallengeDifficulty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;
    
    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "difficulty", nullable = false)
    @Enumerated(EnumType.STRING)
    private ChallengeDifficulty difficulty;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "score", nullable = false)
    private float score;

    @PrePersist
    private void prePersist(){
        this.active = true;
    }

    public Challenge(){} // construtor vazio para o framework

    public Challenge(String title, String description, ChallengeDifficulty difficulty, float score){
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChallengeDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(ChallengeDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    
}
