package com.senior.devrecruitment.controller;

import com.senior.devrecruitment.entities.tecnologia.DTO.DTOListagemTecnologia;
import com.senior.devrecruitment.entities.tecnologia.DTO.DTOTecnologia;
import com.senior.devrecruitment.service.TecnologiaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tecnologia")
public class TecnologiaController {

    @Autowired
    private TecnologiaService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTecnologia(@RequestBody @Valid DTOTecnologia dtoTecnologia) {
        return service.cadastrarTecnologia(dtoTecnologia);
    }

    @GetMapping
    public ResponseEntity<Page<DTOListagemTecnologia>> listarTodasAsTecnologias(@PageableDefault(size = 5, sort = {"nome"}) Pageable page) {
        return ResponseEntity.ok(service.listarTodasAsTecnologias(page));
    }

    @GetMapping("/{idTecnologia}")
    public ResponseEntity<DTOListagemTecnologia> buscarTecnologiaPorId(@PathVariable("idTecnologia") String idTecnologia) {
        return service.buscarTecnologiaPorId(UUID.fromString(idTecnologia));
    }

    @DeleteMapping("/inativar/{idTecnologia}")
    public ResponseEntity inativarTecnologiaPorId(@PathVariable("idTecnologia") String idTecnologia) {
        return service.inativarTecnologiaPorId(UUID.fromString(idTecnologia));
    }
}
