package com.senior.devrecruitment.entities.candidacy;

import com.senior.devrecruitment.entities.candidate.CandidateEntity;
import com.senior.devrecruitment.entities.candidate.CandidateService;
import com.senior.devrecruitment.entities.technology.TechnologyService;
import com.senior.devrecruitment.entities.vacancy.VacancyEntity;
import com.senior.devrecruitment.entities.vacancy.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
public class CandidacyService {

    @Autowired
    private CandidacyRepository candidacyRepository;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private TechnologyService technologyService;

    @Autowired
    private VacancyService vacancyService;

    public Optional<CandidacyEntity> findById(UUID idCandidacy){
        return candidacyRepository.findById(idCandidacy);
    }

    @Transactional
    public void realizarCandidatura(UUID idCandidate, UUID idVacancy) {

        CandidateEntity candidateEntity = candidateService.findCandidateById(idCandidate).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Candidato informado é inválido ou não existe na nossa base"));
        VacancyEntity vacancyEntity = vacancyService.findVacancyById(idVacancy).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Vaga informado é inválida ou não existe na nossa base"));

        if (!candidateEntity.isActive()) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível realizar uma candidatura com um usuário inativo");
        }

        if (!vacancyEntity.isActive()) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível realizar uma candidatura em uma vaga inativa");
        }

        if (candidateEntity.getTechnologies().isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível realizar uma candidatura sem uma tecnologia atribuida ao candidato");
        }

        if (candidateEntity.getTechnologies().stream().noneMatch(x -> x.getRecommendedArea().equals(vacancyEntity.getNeedVacancy()))) {
            throw new ResponseStatusException(BAD_REQUEST, "As suas stacks tecnológicas não são compatíveis com a necessidade da vaga");
        }

        if (candidacyRepository.existsByCandidatesAndVacancies(candidateEntity, vacancyEntity)) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível se candidatar para a mesma vaga duas vezes");
        }

        CandidacyEntity candidacy = new CandidacyEntity();
        candidacy.setCandidateEntity(candidateEntity);
        candidacy.setVacancyEntity(vacancyEntity);
        candidacy.setCandidacyDate(LocalDate.now());

        candidacyRepository.save(candidacy);

        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(candidacy.getIdCandidacy()).toUri();

        ResponseEntity.created(location).body(candidacy);
    }
}
