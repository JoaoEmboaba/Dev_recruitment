package devrecruitment.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import devrecruitment.technology.Technology;
import devrecruitment.technology.TechnologyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private TechnologyService technologyService;

    public List<Technology> listTechnologiesByCandidateId(UUID idCandidate) {
        return candidateRepository.listarTecnologiasPeloIdDoCandidato(idCandidate);
    }

    public List<Candidate> findAllByActiveIsTrue() {
        return candidateRepository.findAllByActiveIsTrue();
    }

    public List<Candidate> findAllByActiveIsFalse() {
        return candidateRepository.findAllByActiveIsFalse();
    }

    public Optional<Candidate> findById(UUID id) {
        return candidateRepository.findById(id);
    }

    public void saveCandidate(Candidate entity) {
        candidateRepository.save(entity);
    }

    public List<Candidate> getAllCandidateByName(String name) {
        return candidateRepository.getCandidateByName(name);
    }

    public void inativateCandidate(UUID idCandidate) {
        var candidate = findById(idCandidate).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "O candidato informado é inválido ou não existe"));
        if (!candidate.getActive()) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível inativar um candidato inativo");
        }
        candidate.setActive(false);
        saveCandidate(candidate);
    }

    @Transactional
    public String assignTechnologyToCandidate(UUID idCandidate, UUID... idTechnology) {

        var candidate = findById(idCandidate).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "O candidato informado é inválido ou não existe"));

        List<Technology> technologies = new ArrayList<>();

        for (UUID tecnologia : idTechnology) {
            var tech = technologyService.findById(tecnologia).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "A tecnologia informada é inválida ou não existe"));

            if (Boolean.FALSE.equals(tech.getActive())) {
                throw new ResponseStatusException(BAD_REQUEST, "Não é possível atribuir uma tecnologia inativa ao candidato");
            }

            technologies.add(tech);
        }

        if (Boolean.FALSE.equals(candidate.getActive())) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível realizar a atribuição de uma tecnologia à um candidato inativo");
        }

        for (Technology technology : candidate.getTechnologies()) {
            for (UUID idTechnologies : idTechnology) {
                if (technology.getId().equals(idTechnologies)) {
                    throw new ResponseStatusException(BAD_REQUEST, "O candidato já possui essa tecnologia atríbuida à ele");
                }
            }
        }

        candidate.getTechnologies().addAll(technologies);
        candidateRepository.save(candidate);

        return "Atribuição concluída com sucesso!!!";
    }

    @Transactional
    public String unassignTechnologyOfCandidate(UUID idCandidate, UUID... idTechnology) {
        var candidate = findById(idCandidate).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "O candidato informado é inválido ou não existe"));

        if (candidate.getTechnologies().isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "O candidato não possui tecnologia para desatribuir");
        }

        candidateRepository.save(candidate);

        return "Desatribuição concluída com sucesso";
    }
}
