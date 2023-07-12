package com.senior.devrecruitment.service.mock;

import com.senior.devrecruitment.entities.candidato.Candidato;
import com.senior.devrecruitment.entities.candidatura.Candidatura;
import com.senior.devrecruitment.entities.vaga.Vaga;
import com.senior.devrecruitment.repository.CandidatoRepository;
import com.senior.devrecruitment.repository.CandidaturaRepository;
import com.senior.devrecruitment.repository.VagaRepository;
import com.senior.devrecruitment.service.CandidaturaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import java.util.Optional;
import java.util.UUID;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidaturaServiceMockTest {

    @InjectMocks
    private CandidaturaService candidaturaService;
    @Mock
    private CandidaturaRepository candidaturaRepository;
    @Mock
    private CandidatoRepository candidatoRepository;
    @Mock
    private VagaRepository vagaRepository;

    private Candidatura candidatura;
    private Candidato candidato;
    private Vaga vaga;

    @Test
    @DisplayName("")
    void testeCenario01(){
        candidatura = new Candidatura();
        candidato = new Candidato();
        vaga = new Vaga();
        candidato.setIdCandidato(UUID.fromString("55ed20d0-1a6c-11ee-be56-0242ac120002"));
        vaga.setIdVaga(UUID.fromString("7b198538-1a6c-11ee-be56-0242ac120002"));
        candidaturaService.realizarCandidatura(candidato.getIdCandidato(), vaga.getIdVaga());
        when(candidaturaRepository.findById(candidatura.getId_candidatura())).thenReturn(Optional.of(candidatura));
        verify(candidaturaRepository).findById(candidatura.getId_candidatura());
        verify(candidatoRepository).findById(candidato.getIdCandidato());
        verify(vagaRepository).findById(vaga.getIdVaga());
    }
}
