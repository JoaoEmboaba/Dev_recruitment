package com.senior.devrecruitment.service;

import com.senior.devrecruitment.entities.candidate.CandidateEntity;
import com.senior.devrecruitment.entities.candidate.CandidateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CandidateEntityServiceJTest {

    @Autowired
    private CandidateService candidateService;

    @Test
    @DisplayName("OK - Teste com o UUID sendo puxado da entidade, retornando true")
    void testeCenario01(){
        CandidateEntity candidateEntity = new CandidateEntity();
        CandidateService candidatoService = new CandidateService();
        candidateEntity.setIdCandidate(UUID.fromString("508b0552-131b-4dd1-b515-ecde13c64e48"));
        candidateService.findCandidateById(candidateEntity.getIdCandidate());
        Assertions.assertTrue(candidatoService.checkIfCandidateIsInactive(candidateEntity.getIdCandidate()));
    }
}
