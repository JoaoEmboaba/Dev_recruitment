package com.service;

import devrecruitment.candidate.Candidate;
import devrecruitment.candidate.CandidateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class CandidateServiceJTest {

    @Autowired
    private CandidateService candidateService;

    @Test
    @DisplayName("OK - Teste com o UUID sendo puxado da entidade, retornando true")
    void testeCenario01(){
        Candidate candidate = new Candidate();
        CandidateService candidatoService = new CandidateService();
        candidate.setId(UUID.fromString("508b0552-131b-4dd1-b515-ecde13c64e48"));
        candidateService.findById(candidate.getId());
    }
}
