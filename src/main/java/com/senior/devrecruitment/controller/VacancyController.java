package com.senior.devrecruitment.controller;

import com.senior.devrecruitment.entities.vacancy.DTO.DTOListVacancies;
import com.senior.devrecruitment.entities.vacancy.DTO.DTOVacancy;
import com.senior.devrecruitment.entities.vacancy.VacancyEntity;
import com.senior.devrecruitment.entities.vacancy.VacancyService;
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
@RequestMapping("/vaga")
public class VacancyController {

    @Autowired
    private VacancyService service;



    @PostMapping
    @Transactional
    public ResponseEntity<DTOVacancy> createVacancy(@RequestBody @Valid DTOVacancy dtoVacancy) {
        service.createVacancy(dtoVacancy);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoVacancy);
    }

    @GetMapping
    public ResponseEntity<Page<DTOListVacancies>> listAllVacancies(
            @PageableDefault(size = 5, sort = {"description"}) Pageable page) {
        return ResponseEntity.ok(service.listAllVacancies(page));
    }

    @GetMapping("/{idVaga}")
    public Optional<VacancyEntity> findVacancyById(@PathVariable("idVaga") UUID idVaga) {
        return service.findVacancyById(idVaga);
    }

    @DeleteMapping("/inativar/{idVaga}")
    public ResponseEntity<VacancyEntity> inativarVagaPorId(@PathVariable("idVaga") UUID idVaga) {
        return service.inativateVacancy(idVaga);
    }
}
