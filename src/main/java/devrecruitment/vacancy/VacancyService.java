package devrecruitment.vacancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class VacancyService {

    @Autowired
    private VacancyRepository vacancyRepository;

    public Optional<Vacancy> findById(UUID id) {
        return vacancyRepository.findById(id);
    }

    public List<Vacancy> findAllByActiveIsFalse() {
        return vacancyRepository.findAllByActiveIsFalse();
    }

    public List<Vacancy> findAllByActiveIsTrue() {
        return vacancyRepository.findAllByActiveIsTrue();
    }

    public void inativateVacancy(UUID idVacancy) {
        Vacancy vacancy = findById(idVacancy).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "A vaga informada é inválida ou não existe"));
        if (!vacancy.getActive().booleanValue()) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível atribuir inativar uma vaga já inativa");
        }
        vacancy.setActive(false);
        vacancyRepository.save(vacancy);
    }
}
