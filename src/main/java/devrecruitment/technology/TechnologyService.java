package devrecruitment.technology;

import devrecruitment.enums.Framework;
import devrecruitment.enums.Language;
import devrecruitment.enums.PerformanceArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    public Optional<Technology> findById(UUID id) {
        return technologyRepository.findById(id);
    }

    public List<Technology> findAllById(UUID... idTechnology){
        return technologyRepository.findAllById(Arrays.asList(idTechnology));
    }

    public void save(Technology entity) {
        technologyRepository.save(entity);
    }

    public List<Technology> findAllByActiveIsTrue() {
        return technologyRepository.findAllByActiveIsTrue();
    }

    public List<Technology> findAllByActiveIsFalse() {
        return technologyRepository.findAllByActiveIsFalse();
    }


    public void validateTechnology(Technology entity) {
        Language language = entity.getName();
        Framework framework = entity.getFramework();

        if (technologyExists(language, framework)) {
            throw new ResponseStatusException(BAD_REQUEST, "A tecnologia informada já existe na nossa base");
        }

        Map<Language, List<Framework>> validFrameworks = new EnumMap<>(Language.class);
        collectionWithFrameworkCompatibleLanguages(validFrameworks);

        if (!validFrameworks.containsKey(language) || !validFrameworks.get(language).contains(framework)) {
            throw new ResponseStatusException(BAD_REQUEST, "O framework não é compatível com a linguagem");
        }

        if (language.equals(Language.JAVA)) {
            entity.setRecommendedArea(PerformanceArea.BACKEND);
        } else if (language.equals(Language.JS) || language.equals(Language.JAVASCRIPT)) {
            entity.setRecommendedArea(PerformanceArea.FRONTEND);
        } else if (language.equals(Language.LUA)) {
            entity.setRecommendedArea(PerformanceArea.GAME_DEVELOPMENT);
        } else if (language.equals(Language.PYTHON)) {
            entity.setRecommendedArea(PerformanceArea.BIGDATA);
        }
    }

    private void collectionWithFrameworkCompatibleLanguages(Map<Language, List<Framework>> validFrameworks) {
        validFrameworks.put(Language.JAVA, Arrays.asList(Framework.SPRING_BOOT, Framework.SPRING));
        validFrameworks.put(Language.JS, Arrays.asList(Framework.NEXT, Framework.ANGULAR, Framework.NEXTJS));
        validFrameworks.put(Language.JAVASCRIPT, Arrays.asList(Framework.NEXT, Framework.ANGULAR, Framework.NEXTJS));
        validFrameworks.put(Language.LUA, Collections.singletonList(Framework.OPENRESTY));
        validFrameworks.put(Language.PYTHON, Collections.singletonList(Framework.NUMPY));
    }

    private boolean technologyExists(Language language, Framework framework) {
        return technologyRepository.technologyExists(language, framework);

    }

    public void inactivateTechnology(UUID idTech) {
        Technology technology = findById(idTech).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "A tecnologia informada é inválida ou não existe"));
        if (!technology.getActive()) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível inativar uma tecnologia inativa");
        }
        technology.setActive(false);
        technologyRepository.save(technology);
    }
}
