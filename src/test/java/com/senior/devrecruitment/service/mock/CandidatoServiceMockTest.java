package com.senior.devrecruitment.service.mock;

import com.senior.devrecruitment.entities.candidato.Candidato;
import com.senior.devrecruitment.exception.NotFoundException;
import com.senior.devrecruitment.repository.CandidatoRepository;

import java.util.Optional;
import java.util.UUID;

import com.senior.devrecruitment.service.CandidatoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidatoServiceMockTest {

    @InjectMocks
    private CandidatoService candidatoService;
    @Mock
    private CandidatoRepository candidatoRepository;
    private Candidato candidato;

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    @DisplayName("NOK - Teste com os UUID's diferentes, retornando a NotFoundException")
    void testeCenario01() {
        candidato = new Candidato();
        candidato.setIdCandidato(UUID.fromString("508b0552-131b-4dd1-b515-ecde13c64e48"));
        when(candidatoRepository.findById(candidato.getIdCandidato())).thenReturn(Optional.of(candidato));
        assertThat(catchThrowable(() -> candidatoService.verificarSeCandidatoEstaInativo(UUID.fromString("508b0552-131b-4dd1-b514-ecde13c64e48"))))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("OK - Teste com o UUID sendo puxado da própria entidade, retornando true")
    void testeCenario02() {
        candidato = new Candidato();
        candidato.setIdCandidato(UUID.fromString("508b0552-131b-4dd1-b515-ecde13c64e48"));
        when(candidatoRepository.findById(candidato.getIdCandidato())).thenReturn(Optional.of(candidato));
        assertThat(candidatoService.verificarSeCandidatoEstaInativo(candidato.getIdCandidato())).isTrue();
    }

    @Test
    @DisplayName("NOK - Teste com o objeto vazio, lançando a exceção not found")
    void testeCenario03(){
        candidato = new Candidato();
        candidato.setIdCandidato(UUID.fromString("508b0552-131b-4dd1-b515-ecde13c64e48"));
        when(candidatoRepository.findById(candidato.getIdCandidato())).thenReturn(Optional.empty());
        assertThat(catchThrowable(() -> candidatoService.verificarSeCandidatoEstaInativo(candidato.getIdCandidato())))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("OK - Teste para verificar se o usuário é inativado corretamente")
    void testeCenario04(){
        candidato = new Candidato();
        candidato.setIdCandidato(UUID.fromString("508b0552-131b-4dd1-b515-ecde13c64e48"));
        when(candidatoRepository.findById(candidato.getIdCandidato())).thenReturn(Optional.of(candidato));
        candidatoService.inativarPorId(candidato.getIdCandidato());
        assertThat(candidato.isAtivo()).isFalse();
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    @DisplayName("OK - Teste para verificar se o usuário é excluído corretamente")
    void testeCenario05(){
        candidato = new Candidato();
        candidato.setIdCandidato(UUID.fromString("508b0552-131b-4dd1-b515-ecde13c64e48"));
        when(candidatoRepository.findById(candidato.getIdCandidato())).thenReturn(Optional.of(candidato));
        candidatoService.excluirPorId(candidato.getIdCandidato());
        verify(candidatoRepository).findById(candidato.getIdCandidato());
    }

    @Test
    @DisplayName("NOK - Testando a verificação se está ativo ou inativo com o id nulo")
    void testeCenario06(){
        assertThat(catchThrowable(() -> candidatoService.verificarSeCandidatoEstaInativo(
                UUID.fromString("19a59ade-1b67-11ee-be56-0242ac120002")))).isInstanceOf(NotFoundException.class);
    }
}
