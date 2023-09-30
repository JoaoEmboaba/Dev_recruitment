package com.senior.devrecruitment.entities.candidate;

import com.senior.devrecruitment.entities.candidate.DTO.DTOCandidate;
import com.senior.devrecruitment.entities.candidate.DTO.DTOListCandidates;
import com.senior.devrecruitment.entities.technology.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.*;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private TechnologyService technologyService;

    public CandidateEntity save(CandidateEntity candidate){
        return candidateRepository.save(candidate);
    }

    public ResponseEntity<CandidateEntity> createCandidate(DTOCandidate dtoCandidate) {
        var candidate = candidateRepository.save(new CandidateEntity(dtoCandidate));
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(candidate.getIdCandidate()).toUri();
        return created(location).body(candidate);
    }

    public Page<DTOListCandidates> listAllHiredCandidates(Pageable page) {
        return candidateRepository.findAllByContratadoTrue(page).map(DTOListCandidates::new);
    }

    public boolean checkIfCandidateIsInactive(UUID idCandidate) {
        var candidate = candidateRepository.findById(idCandidate).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "O candidato informado não foi encontrado na nossa base"));
        return candidate.isActive();
    }

    public Page<DTOListCandidates> listAllNotHiredCandidates(Pageable page) {
        return candidateRepository.findAllByContratadoFalse(page).map(DTOListCandidates::new);
    }

    public Page<DTOListCandidates> listAllCandidates(Pageable page) {
        return candidateRepository.findAll(page).map(DTOListCandidates::new);
    }

    public Optional<CandidateEntity> findCandidateById(UUID idCandidate) {
        return candidateRepository.findById(idCandidate);
    }

    public ResponseEntity<CandidateEntity> inativateById(UUID idCandidate) {
        candidateRepository.findById(idCandidate).ifPresent(candidato -> {
            candidato.setActive(false);
            candidateRepository.save(candidato);
        });
        return noContent().build();
    }

    public void assignTechnologyToCandidate(UUID idCandidato, UUID idTecnologia) {

        var candidate = candidateRepository.findById(idCandidato).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "O candidato informado não existe na nossa base"));
        var technology = technologyService.findTechnologyById(idTecnologia).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "A tecnologia que você informou não existe na nossa base"));

        if (!candidate.isActive()) {
            throw new ResponseStatusException(BAD_REQUEST, "O candidato que está recebendo a atribuição da tecnologia está inativo!");
        }

        if (!technology.isActive()) {
            throw new ResponseStatusException(BAD_REQUEST, "A tecnologia que está sendo atribuida ao candidato está inativa!");
        }

        candidate.getTechnologies().add(technology);
        technology.getCandidateEntities().add(candidate);

        technologyService.createTechnology(technology);
        candidateRepository.save(candidate);

        ok("Tecnologia atribuída com sucesso ao candidate");
    }
}
