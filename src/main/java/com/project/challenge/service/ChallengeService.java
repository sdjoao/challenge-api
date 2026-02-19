package com.project.challenge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.challenge.dto.ChallengeReportDTO;
import com.project.challenge.dto.ChallengeRequestDTO;
import com.project.challenge.dto.ChallengeResponseDTO;
import com.project.challenge.entity.Challenge;
import com.project.challenge.enums.ChallengeDifficulty;
import com.project.challenge.exceptions.BusinessException;
import com.project.challenge.mapper.ChallengeMapper;
import com.project.challenge.repository.ChallengeRepository;

@Service
public class ChallengeService {
    
    private final ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository){
        this.challengeRepository = challengeRepository;
    }

    //  verifica se a requisição está válida
    private boolean isValid(ChallengeRequestDTO request){
        if(request.title() == null || request.title().isBlank()){
            throw new BusinessException("Título inválido ou não preenchido.");
        }
        if(request.description() == null || request.description().isBlank()){
            throw new BusinessException("Descrição inválida ou não preenchida.");
        }
        if(request.difficulty() != ChallengeDifficulty.EASY && request.difficulty() != ChallengeDifficulty.MEDIUM && request.difficulty() != ChallengeDifficulty.HARD){
            throw new BusinessException("Tipo de dificuldade inválido.");
        }
        if(request.score() <= 0){
            throw new BusinessException("Pontuação de score inválida, é necessário ter valor positivo.");
        }
        return true;
    }

    private Challenge findById(Long id){
        return challengeRepository.findById(id).orElseThrow(() -> new BusinessException("Desafio com ID " + id + " não encontrado."));
    }

    public ChallengeResponseDTO createChallenge(ChallengeRequestDTO request){
        isValid(request);
        Challenge challenge = new Challenge();
        challenge.setTitle(request.title());
        challenge.setDescription(request.description());
        challenge.setDifficulty(request.difficulty());
        challenge.setScore(request.score());
        challengeRepository.save(challenge);
        return ChallengeMapper.conversor(challenge);
    }

    public List<ChallengeResponseDTO> getChallenges(){
        return ChallengeMapper.listConversor(challengeRepository.findAll());
    }

    public ChallengeResponseDTO getChallengeById(Long id){
        return ChallengeMapper.conversor(findById(id));
    }

    public ChallengeResponseDTO updateChallenge(ChallengeRequestDTO request, Long id){
        Challenge challenge = findById(id);
        challenge.setTitle(request.title());
        challenge.setDescription(request.description());
        challenge.setDifficulty(request.difficulty());
        challenge.setScore(request.score());
        challengeRepository.save(challenge);
        return ChallengeMapper.conversor(challenge);
    }

    public ChallengeResponseDTO evaluete(Long id){
        Challenge challenge = findById(id);
        if(!challenge.isActive()){
            throw new BusinessException("Desáfio inátivo, não é possível adicionar score.");
        }
        if(challenge.getScore() > 100){
            challenge.setActive(false);
            challengeRepository.save(challenge);
        }
        switch (challenge.getDifficulty().name()) {
            case "EASY":
                challenge.setScore(challenge.getScore() + 10);
                break;
            case "MEDIUM":
                challenge.setScore(challenge.getScore() + 20);
                break;
            case "HARD":
                challenge.setScore(challenge.getScore() + 30);
                break;
            default:
                break;
        }
        challengeRepository.save(challenge);
        return ChallengeMapper.conversor(challenge);
    }

    public void deleteChallenge(Long id){
        Challenge challenge = findById(id);
        challengeRepository.delete(challenge);
    }

    public List<ChallengeResponseDTO> ranking(){
        return ChallengeMapper.listConversor(challengeRepository.findChallengeActiveByScore());
    }

    // método para gerar relatórios de desáfios
    public List<ChallengeReportDTO> getChallengeReportData(){
        return challengeRepository.findAll()
            .stream()
            .map(challenge ->  new ChallengeReportDTO(
                    challenge.getId(),
                    challenge.getTitle(),
                    challenge.getDifficulty().name(),
                    challenge.getScore(),
                    challenge.isActive()
            ))
            .toList();
    }
    
}
