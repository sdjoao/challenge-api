package com.project.challenge.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.challenge.dto.ChallengeReportDTO;
import com.project.challenge.dto.ChallengeRequestDTO;
import com.project.challenge.dto.ChallengeResponseDTO;
import com.project.challenge.service.ChallengeService;
import com.project.challenge.service.ReportService;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {
    
    private final ChallengeService challengeService;
    private final ReportService reportService;

    public ChallengeController(ChallengeService challengeService, ReportService reportService){
        this.challengeService = challengeService;
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<ChallengeResponseDTO> createChallenge(@RequestBody ChallengeRequestDTO request){
        return ResponseEntity.status(201).body(challengeService.createChallenge(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChallengeResponseDTO> getChallengeById(@PathVariable Long id){
        return ResponseEntity.ok().body(challengeService.getChallengeById(id));
    }

    @GetMapping
    public ResponseEntity<List<ChallengeResponseDTO>> getChallenges(){
        return ResponseEntity.ok().body(challengeService.getChallenges());
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<ChallengeResponseDTO>> getRanking(){
        return ResponseEntity.ok().body(challengeService.ranking());
    }

    // endpoint para gerar o relatório
    @GetMapping("/reports/challenges")
    public ResponseEntity<byte[]> generateReport() throws Exception {

        List<ChallengeReportDTO> dados =
                challengeService.getChallengeReportData();

        byte[] pdf =
                reportService.gerarRelatorioDesafios(dados);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=products.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChallengeResponseDTO> updateChallenge(@RequestBody ChallengeRequestDTO request, @PathVariable Long id){
        return ResponseEntity.ok().body(challengeService.updateChallenge(request, id));
    }

    @PutMapping("/{id}/evaluate")
    public ResponseEntity<ChallengeResponseDTO> evaluate(@PathVariable Long id){
        return ResponseEntity.ok().body(challengeService.evaluete(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long id){
        challengeService.deleteChallenge(id);
        return ResponseEntity.noContent().build();
    }

}
