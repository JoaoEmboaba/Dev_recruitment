package com.senior.devrecruitment.service.mock;

import com.senior.devrecruitment.entities.candidate.CandidateEntity;
import com.senior.devrecruitment.entities.candidate.CandidateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateEntityServiceMockTest {

    @InjectMocks
    private CandidateService candidateService;
    @Captor
    ArgumentCaptor<CandidateEntity> candidatoCaptor;
    private CandidateEntity candidateEntity;

    @Test
    @DisplayName("NOK - Teste com os UUID's diferentes, retornando a NotFoundException")
    void testeCenario01() {
        assertThat(catchThrowable(() -> candidateService.checkIfCandidateIsInactive(UUID.fromString("508b0552-131b-4dd1-b514-ecde13c64e48")))).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("OK - Teste com o UUID sendo puxado da própria entidade, retornando true")
    void testeCenario02() {
        candidateEntity = new CandidateEntity();
        candidateEntity.setIdCandidate(UUID.fromString("508b0552-131b-4dd1-b515-ecde13c64e48"));
        when(candidateService.findCandidateById(candidateEntity.getIdCandidate())).thenReturn(Optional.of(candidateEntity));
        assertThat(candidateService.checkIfCandidateIsInactive(candidateEntity.getIdCandidate())).isTrue();
    }

    @Test
    @DisplayName("NOK - Teste com o objeto vazio, lançando a exceção not found")
    void testeCenario03() {
        candidateEntity = new CandidateEntity();
        candidateEntity.setIdCandidate(UUID.fromString("508b0552-131b-4dd1-b515-ecde13c64e48"));
        when(candidateService.findCandidateById(candidateEntity.getIdCandidate())).thenReturn(Optional.empty());
        assertThat(catchThrowable(() -> candidateService.checkIfCandidateIsInactive(candidateEntity.getIdCandidate()))).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("OK - Teste para verificar se o usuário é inativado corretamente")
    void testeCenario04() {
        candidateEntity = new CandidateEntity();
        candidateEntity.setIdCandidate(UUID.fromString("508b0552-131b-4dd1-b515-ecde13c64e48"));
        when(candidateService.findCandidateById(candidateEntity.getIdCandidate())).thenReturn(Optional.of(candidateEntity));
        candidateService.inativateById(candidateEntity.getIdCandidate());
        assertThat(candidateEntity.isActive()).isFalse();
        verify(candidateService).save(candidatoCaptor.capture());
    }

    @Test
    @DisplayName("NOK - Testando a verificação se está ativo ou inativo com o id nulo")
    void testeCenario06() {
        assertThat(catchThrowable(() -> candidateService.checkIfCandidateIsInactive(UUID.fromString("19a59ade-1b67-11ee-be56-0242ac120002")))).isInstanceOf(NotFoundException.class);
    }
}
