package com.senior.devrecruitment.service;

import com.senior.devrecruitment.entities.candidato.Candidato;
import com.senior.devrecruitment.repository.CandidatoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CandidatoServiceJTest {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Test
    @DisplayName("OK - Teste com o UUID sendo puxado da entidade, retornando true")
    void testeCenario01(){
        Candidato candidato = new Candidato();
        CandidatoService candidatoService = new CandidatoService();
        candidato.setIdCandidato(UUID.fromString("508b0552-131b-4dd1-b515-ecde13c64e48"));
        candidatoRepository.findById(candidato.getIdCandidato());
        assertEquals(true,
                candidatoService.verificarSeCandidatoEstaInativo(candidato.getIdCandidato()));
    }
}
