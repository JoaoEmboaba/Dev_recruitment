package com.senior.devrecruitment.controller;

import com.senior.devrecruitment.entities.vaga.DTO.DTOListagemVagas;
import com.senior.devrecruitment.entities.vaga.DTO.DTOVaga;
import com.senior.devrecruitment.entities.vaga.Vaga;
import com.senior.devrecruitment.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/vaga")
public class VagaController {

    @Autowired
    private VagaService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarVaga(@RequestBody @Valid DTOVaga vaga) {
        service.cadastrarVaga(new Vaga(vaga));
        return ResponseEntity.status(HttpStatus.CREATED).body(vaga);
    }

    @GetMapping
    public ResponseEntity<Page<DTOListagemVagas>> listarTodasAsVagas(
            @PageableDefault(size = 5, sort = {"descricao"}) Pageable page) {
        return ResponseEntity.ok(service.listarTodasAsVagas(page));
    }

    @GetMapping("/{idVaga}")
    public ResponseEntity<DTOListagemVagas> buscarVagaPorId(@PathVariable("idVaga") String idVaga) {
        return service.buscarVagaPorId(UUID.fromString(idVaga));
    }

    @DeleteMapping("/inativar/{idVaga}")
    public ResponseEntity inativarVagaPorId(@PathVariable("idVaga") String idVaga) {
        return service.inativarVagaPorId(UUID.fromString(idVaga));
    }
}
