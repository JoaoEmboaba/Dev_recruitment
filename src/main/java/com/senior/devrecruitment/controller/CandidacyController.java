package com.senior.devrecruitment.controller;

import com.senior.devrecruitment.entities.candidacy.CandidacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/candidatura")
public class CandidacyController {

    @Autowired
    private CandidacyService service;

    @PostMapping("/{idCandidato}/{idVaga}")
    @Transactional
    public ResponseEntity realizarCandidatura(@PathVariable("idCandidato") String idCandidato,
                                              @PathVariable("idVaga") String idVaga) {

        service.realizarCandidatura(UUID.fromString(idCandidato), UUID.fromString(idVaga));
        return ResponseEntity.status(HttpStatus.CREATED).body("Candidatura realizada com sucesso!!");
    }
}
