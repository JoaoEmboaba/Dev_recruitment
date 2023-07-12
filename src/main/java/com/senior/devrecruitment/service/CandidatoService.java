package com.senior.devrecruitment.service;

import com.senior.devrecruitment.entities.candidato.DTO.DTOCandidato;
import com.senior.devrecruitment.entities.candidato.Candidato;
import com.senior.devrecruitment.entities.candidato.DTO.DTOListagemCandidatos;
import com.senior.devrecruitment.enums.AreaAconselhavel;
import com.senior.devrecruitment.enums.Framework;
import com.senior.devrecruitment.enums.Linguagens;
import com.senior.devrecruitment.exception.NotFoundException;
import com.senior.devrecruitment.projection.TecnologiaProjection;
import com.senior.devrecruitment.repository.CandidatoRepository;
import com.senior.devrecruitment.repository.TecnologiaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.http.ResponseEntity.*;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    public List<TecnologiaProjection> buscarTecnologiasDoCandidato(UUID idCandidato) {
        return candidatoRepository.findTecnologiasByIdCandidato(idCandidato);
    }

    public void cadastrarCandidato(DTOCandidato dtoCandidato) {
        candidatoRepository.save(new Candidato(dtoCandidato));
    }

    public Page<DTOListagemCandidatos> listarCandidatosContratados(Pageable page) {
        return candidatoRepository.findAllByContratadoTrue(page).map(DTOListagemCandidatos::new);
    }

    public boolean verificarSeCandidatoEstaInativo(UUID idCandidato) {
        Optional<Candidato> candidato = candidatoRepository.findById(idCandidato);
        if (candidato.isEmpty()) {
            throw new NotFoundException();
        }
        return candidato.get().isAtivo();
    }

    public Page<DTOListagemCandidatos> listarCandidatosNaoContratados(Pageable page) {
        return candidatoRepository.findAllByContratadoFalse(page).map(DTOListagemCandidatos::new);
    }

    public Page<DTOListagemCandidatos> listarTodosOsCandidatos(Pageable page) {
        return candidatoRepository.findAll(page).map(DTOListagemCandidatos::new);
    }

    public ResponseEntity<DTOListagemCandidatos> buscarCandidatoPorId(UUID idCandidato) {
        return ResponseEntity.ok(new DTOListagemCandidatos(candidatoRepository.getReferenceById(idCandidato)));
    }

    public ResponseEntity inativarPorId(UUID idCandidato) {
        candidatoRepository.findById(idCandidato).ifPresent(candidato -> {
            candidato.setAtivo(false);
            candidatoRepository.save(candidato);
        });
        return noContent().build();
    }

    public void excluirPorId(UUID idCandidato) {
        if (candidatoRepository.findById(idCandidato).isEmpty()) {
            throw new NotFoundException();
        }
        candidatoRepository.deleteById(idCandidato);
    }

    public ResponseEntity<String> atribuirTecnologiaAoCandidato(UUID idCandidato, UUID idTecnologia) {

        var candidato = candidatoRepository.findById(idCandidato).orElse(null);
        var tecnologia = tecnologiaRepository.findById(idTecnologia).orElse(null);

        if (candidato == null || tecnologia == null) {
            ResponseEntity.badRequest().body("id do candidato ou tecnologia não encontrado");
        }

        if (!candidato.isAtivo()) {
            throw new RuntimeException("O candidato que está recebendo a atribuição da tecnologia está inativo!");
        }

        if (!tecnologia.isAtivo()) {
            throw new RuntimeException("A tecnologia que está sendo atribuida ao candidato está inativa!");
        }

        candidato.getTecnologiasCandidatoes().add(tecnologia);
        tecnologia.getCandidatoes().add(candidato);

        tecnologiaRepository.save(tecnologia);
        candidatoRepository.save(candidato);

        return ResponseEntity.ok("Tecnologia atribuída com sucesso ao candidato");
    }
}
