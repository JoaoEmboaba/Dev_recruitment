package com.service.mock;

import devrecruitment.candidate.Candidate;
import devrecruitment.candidate.CandidateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceMockTest {

    @InjectMocks
    private CandidateService candidateService;
    @Captor
    ArgumentCaptor<Candidate> candidatoCaptor;
    private Candidate candidate;

    @Test
    @DisplayName("NOK - Teste com os UUID's diferentes, retornando a NotFoundException")
    void testeCenario01() {
//        assertThat(catchThrowable(() -> candidateService.checkIfCandidateIsInactive(UUID.fromString("508b0552-131b-4dd1-b514-ecde13c64e48")))).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("OK - Teste com o UUID sendo puxado da própria entidade, retornando true")
    void testeCenario02() {
        candidate = new Candidate();
        candidate.setId(UUID.fromString("508b0552-131b-4dd1-b515-ecde13c64e48"));
        when(candidateService.findById(candidate.getId())).thenReturn(Optional.of(candidate));
//        assertThat(candidateService.checkIfCandidateIsInactive(candidateEntity.getId())).isTrue();
    }

    @Test
    @DisplayName("NOK - Teste com o objeto vazio, lançando a exceção not found")
    void testeCenario03() {
        candidate = new Candidate();
        candidate.setId(UUID.fromString("508b0552-131b-4dd1-b515-ecde13c64e48"));
        when(candidateService.findById(candidate.getId())).thenReturn(Optional.empty());
//        assertThat(catchThrowable(() -> candidateService.checkIfCandidateIsInactive(candidateEntity.getId()))).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("OK - Teste para verificar se o usuário é inativado corretamente")
    void testeCenario04() {
        candidate = new Candidate();
        candidate.setId(UUID.fromString("508b0552-131b-4dd1-b515-ecde13c64e48"));
        when(candidateService.findById(candidate.getId())).thenReturn(Optional.of(candidate));
        candidateService.inativateCandidate(candidate.getId());
        assertThat(candidate.getActive()).isFalse();
        verify(candidateService).saveCandidate(candidatoCaptor.capture());
    }

    @Test
    @DisplayName("NOK - Testando a verificação se está ativo ou inativo com o id nulo")
    void testeCenario06() {
//        assertThat(catchThrowable(() -> candidateService.checkIfCandidateIsInactive(UUID.fromString("19a59ade-1b67-11ee-be56-0242ac120002")))).isInstanceOf(NotFoundException.class);
    }
}
