package com.senior.devrecruitment.entities.technology;

import com.senior.devrecruitment.entities.candidate.CandidateService;
import com.senior.devrecruitment.entities.technology.DTO.DTOListTechnologies;
import com.senior.devrecruitment.entities.technology.DTO.DTOTechnology;
import com.senior.devrecruitment.enums.Framework;
import com.senior.devrecruitment.enums.Language;
import com.senior.devrecruitment.enums.PerformanceArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.util.Map;
import java.util.EnumMap;
import java.util.Arrays;
import java.util.Collections;

import static org.springframework.http.ResponseEntity.noContent;

@Service
public class TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private CandidateService candidateService;

    public List<TechnologyProjection> listAllTechnologiesAssignToCandidate(UUID idCandidate) {
        return technologyRepository.listAllTechnologiesAssignToCandidate(idCandidate);
    }

    public Optional<TechnologyEntity> findTechnologyById(UUID idTech){
        return technologyRepository.findById(idTech);
    }

    public void createTechnology(TechnologyEntity technology){

        validateTechnology(technology);
        var savedTechnology = technologyRepository.save(technology);

        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedTechnology.getIdTech())
                .toUri();

        ResponseEntity.created(location).body(savedTechnology);
    }

    public void validateTechnology(TechnologyEntity technologyEntity) {
        Language language = technologyEntity.getName();
        Framework framework = technologyEntity.getFramework();

        if (technologyExists(language, framework)) {
            ResponseEntity.badRequest().body("A tecnologia que você está tentando cadastrar já existe na nossa base");
        }

        Map<Language, List<Framework>> validFrameworks = new EnumMap<>(Language.class);
        collectionWithFrameworkCompatibleLanguages(validFrameworks);

        if (!validFrameworks.containsKey(language) || !validFrameworks.get(language).contains(framework)) {
            ResponseEntity.badRequest().body("O framework não é compatível com a linguagem");
        }

        if (language.equals(Language.JAVA)) {
            technologyEntity.setRecommendedArea(List.of(PerformanceArea.BACKEND));
        } else if (language.equals(Language.JS) || language.equals(Language.JAVASCRIPT)) {
            technologyEntity.setRecommendedArea(List.of(PerformanceArea.FRONTEND));
        } else if (language.equals(Language.LUA)) {
            technologyEntity.setRecommendedArea(List.of(PerformanceArea.GAME_DEVELOPMENT));
        } else if (language.equals(Language.PYTHON)) {
            technologyEntity.setRecommendedArea(List.of(PerformanceArea.BIGDATA));
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

    public ResponseEntity<TechnologyEntity> inactivateTechnology(UUID idTech) {
        TechnologyEntity technologyEntity = findTechnologyById(idTech).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        if (!technologyEntity.isActive()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        technologyEntity.setActive(false);
        technologyRepository.save(technologyEntity);

        return noContent().build();
    }

    public Page<DTOListTechnologies> listAllTechnologies(Pageable page) {
        return technologyRepository.findAll(page).map(DTOListTechnologies::new);
    }
}
