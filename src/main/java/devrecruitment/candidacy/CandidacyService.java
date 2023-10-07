package devrecruitment.candidacy;

import devrecruitment.candidate.Candidate;
import devrecruitment.candidate.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import devrecruitment.vacancy.Vacancy;
import devrecruitment.vacancy.VacancyService;

import java.time.Instant;
import java.util.List;
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
    private VacancyService vacancyService;


    public Optional<Candidacy> findById(UUID idCandidate){
        return candidacyRepository.findById(idCandidate);
    }

    public List<Candidate> listCandidatesWhoAreSuitableForThatVacancy(UUID idVacancy) {
        return candidacyRepository.listCandidatesWhoAreSuitableForThatVacancy(idVacancy);
    }

    public List<Candidate> listCandidatesWhoAreSuitableForThatVacancyAndAreNotHired(UUID idVacancy) {
        return candidacyRepository.listCandidatesWhoAreSuitableForThatVacancyAndAreNotHired(idVacancy);
    }

    public void saveCandidacy(Candidacy candidacy){
        candidacyRepository.save(candidacy);
    }

    @Transactional
    public String makeAnApplication(UUID idCandidate, UUID idVacancy) {

        var candidate = candidateService.findById(idCandidate).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "O candidato informado é inválido ou não existe"));
        Vacancy vacancy = vacancyService.findById(idVacancy).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "A vaga informado é inválida ou não existe "));

        if (Boolean.FALSE.equals(candidate.getActive())) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível realizar uma candidatura com o usuário inativo");
        }

        if (Boolean.FALSE.equals(vacancy.getActive())) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível aplicar para uma vaga inativa");
        }

        if (candidate.getTechnologies().isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível realizar uma candidatura sem uma tecnologia atribuída ao candidato");
        }

        if (candidate.getTechnologies().stream().noneMatch(x -> x.getRecommendedArea().equals(vacancy.getNeedVacancy()))) {
            throw new ResponseStatusException(BAD_REQUEST, "As suas stacks tecnológicas não são compatíveis com as necessidade da vaga");
        }

        if (candidacyRepository.existsByCandidatesAndVacancies(candidate, vacancy)) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível realizar uma candidatura duas vezes na mesma vaga");
        }

        Candidacy candidacy = new Candidacy();
        candidacy.setCandidates(candidate);
        candidacy.setVacancies(vacancy);
        candidacy.setCandidacyDate(Instant.now());

        candidacyRepository.save(candidacy);

        return "Candidatura realizada com sucesso!!";
    }
}

