package com.senior.devrecruitment.controller;

import com.senior.devrecruitment.entities.candidate.CandidateEntity;
import com.senior.devrecruitment.entities.candidate.CandidateService;
import com.senior.devrecruitment.entities.candidate.DTO.DTOCandidate;
import com.senior.devrecruitment.entities.candidate.DTO.DTOListCandidates;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/{idCandidate}/{idTech }")
    @Transactional
    public void atribuirTecnologiaAoCandidato(@PathVariable("idCandidato") String idCandidato, @PathVariable("idTech") String idTech) {
        candidateService.assignTechnologyToCandidate(UUID.fromString(idCandidato), UUID.fromString(idTech));
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarCandidato(@RequestBody @Valid DTOCandidate dtoCandidate) {
        candidateService.createCandidate(dtoCandidate);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoCandidate);
    }

    @GetMapping("/contratados")
    public ResponseEntity<Page<DTOListCandidates>> listarContratados(@PageableDefault(size = 10, sort = {"name"}) Pageable paginacao) {
        return ResponseEntity.ok(candidateService.listAllHiredCandidates(paginacao));
    }

    @GetMapping("/naoContratados")
    public ResponseEntity<Page<DTOListCandidates>> listarNaoContratados(@PageableDefault(size = 10, sort = {"name"}) Pageable paginacao) {
        return ResponseEntity.ok(candidateService.listAllNotHiredCandidates(paginacao));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<DTOListCandidates>> listarTodos(@PageableDefault(size = 10, sort = {"name"}) Pageable paginacao) {
        return ResponseEntity.ok(candidateService.listAllCandidates(paginacao));
    }

    @GetMapping("/{idCandidato}")
    public Optional<CandidateEntity> buscarCandidatoPorID(@PathVariable("idCandidato") String idCandidato) {
        return candidateService.findCandidateById(UUID.fromString(idCandidato));
    }

    @DeleteMapping("inativar/{idCandidato}")
    public ResponseEntity<CandidateEntity> inativarCandidatoPorId(@PathVariable("idCandidato") String idCandidato) {
        return candidateService.inativateById(UUID.fromString(idCandidato));
    }
}
