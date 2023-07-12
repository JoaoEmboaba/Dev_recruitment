package com.senior.devrecruitment.controller;

import com.senior.devrecruitment.entities.candidato.Candidato;
import com.senior.devrecruitment.entities.candidato.DTO.DTOCandidato;
import com.senior.devrecruitment.entities.candidato.DTO.DTOListagemCandidatos;
import com.senior.devrecruitment.projection.TecnologiaProjection;
import com.senior.devrecruitment.repository.CandidatoRepository;
import com.senior.devrecruitment.service.CandidatoService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

    @Autowired
    private CandidatoService service;
    @Autowired
    private CandidatoRepository candidatoRepository;

    @PostMapping("/{idCandidato}/{idTech}")
    @Transactional
    public ResponseEntity atribuirTecnologiaAoCandidato(@PathVariable("idCandidato") String idCandidato, @PathVariable("idTech") String idTech) {
        service.atribuirTecnologiaAoCandidato(UUID.fromString(idCandidato), UUID.fromString(idTech));
        return ResponseEntity.status(HttpStatus.CREATED).body("Atribuição concluída com sucesso!");
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarCandidato(@RequestBody @Valid DTOCandidato dtoCandidato) {
        service.cadastrarCandidato(dtoCandidato);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoCandidato);
    }

    @GetMapping("/contratados")
    public ResponseEntity<Page<DTOListagemCandidatos>> listarContratados(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(service.listarCandidatosContratados(paginacao));
    }

    @GetMapping("/naoContratados")
    public ResponseEntity<Page<DTOListagemCandidatos>> listarNaoContratados(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(service.listarCandidatosNaoContratados(paginacao));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<DTOListagemCandidatos>> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(service.listarTodosOsCandidatos(paginacao));
    }

    @GetMapping("/{idCandidato}")
    public ResponseEntity<DTOListagemCandidatos> buscarCandidatoPorID(@PathVariable("idCandidato") String idCandidato) {
        return service.buscarCandidatoPorId(UUID.fromString(idCandidato));
    }

    @DeleteMapping("inativar/{idCandidato}")
    public ResponseEntity inativarCandidatoPorId(@PathVariable("idCandidato") String idCandidato) {
        return service.inativarPorId(UUID.fromString(idCandidato));
    }

    @GetMapping("/tecnologias/{idTech}")
    public List<TecnologiaProjection> obterTecnologiaDoCandidato(@PathVariable("idTech") UUID idCandidato) {
        Candidato candidato = candidatoRepository.findById(idCandidato).orElse(null);
        return service.buscarTecnologiasDoCandidato(idCandidato);
    }
}
