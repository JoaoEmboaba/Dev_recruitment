package com.senior.devrecruitment.controller;

import com.senior.devrecruitment.entities.technology.DTO.DTOListTechnologies;
import com.senior.devrecruitment.entities.technology.DTO.DTOTechnology;
import com.senior.devrecruitment.entities.technology.TechnologyEntity;
import com.senior.devrecruitment.entities.technology.TechnologyProjection;
import com.senior.devrecruitment.entities.technology.TechnologyService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/technology")
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    @PostMapping
    @Transactional
    public void cadastrarTecnologia(@RequestBody @Valid TechnologyEntity technologyEntity) {
        technologyService.createTechnology(technologyEntity);
    }

    @GetMapping
    public ResponseEntity<Page<DTOListTechnologies>> listAllTechnologies(@PageableDefault(size = 5, sort = {"name"}) Pageable page) {
        return ResponseEntity.ok(technologyService.listAllTechnologies(page));
    }

    @GetMapping("/{idTecnologia}")
    public Optional<TechnologyEntity> findTechnologyById(@PathVariable("idTech") String idTech) {
        return technologyService.findTechnologyById(UUID.fromString(idTech));
    }

    @DeleteMapping("/inativar/{idTech}")
    public ResponseEntity<TechnologyEntity> inactivateTechnologyById(@PathVariable("idTech") String idTech) {
        return technologyService.inactivateTechnology(UUID.fromString(idTech));
    }

    @GetMapping("/tecnologias/{idTech}")
    public List<TechnologyProjection> listTechnologiesAssignToCandidate(@PathVariable("idTech") UUID idCandidato) {
        return technologyService.listAllTechnologiesAssignToCandidate(idCandidato);
    }
}
