package com.senior.devrecruitment.repository;

import com.senior.devrecruitment.entities.candidate.CandidateEntity;
import com.senior.devrecruitment.entities.candidate.CandidateService;
import com.senior.devrecruitment.entities.candidate.DTO.DTOCandidate;
import com.senior.devrecruitment.entities.technology.TechnologyEntity;
import com.senior.devrecruitment.entities.technology.TechnologyService;
import com.senior.devrecruitment.enums.Framework;
import com.senior.devrecruitment.enums.Language;
import com.senior.devrecruitment.enums.PerformanceArea;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CandidateEntityRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private TechnologyService technologyService;

    @Test
    void findTecnologiasByIdCandidato() {

        var candidateEntity = candidateService.save(CandidateEntity.builder().name("João").nacionality("Brasileiro").birthDate(LocalDate.now()).hired(true).build());

        TechnologyEntity technologyEntity1 = TechnologyEntity.builder().name(Language.JS).framework(Framework.EXPRESS).recommendedArea(List.of(PerformanceArea.BACKEND)).description("Linguagem de tipagem fraca").candidateEntities(List.of(candidateEntity)).build();
        technologyService.createTechnology(technologyEntity1);

        TechnologyEntity technologyEntity2 = TechnologyEntity.builder().name(Language.JS).framework(Framework.EXPRESS).recommendedArea(List.of(PerformanceArea.BIGDATA)).description("Linguagem de tipagem fraca").candidateEntities(List.of(candidateEntity)).build();
        technologyService.createTechnology(technologyEntity2);

        candidateEntity.setTechnologies(List.of(technologyEntity1, technologyEntity2));
        technologyEntity1.setCandidateEntities(List.of(candidateEntity));
        technologyEntity2.setCandidateEntities(List.of(candidateEntity));

        assertThat(technologyService.listAllTechnologiesAssignToCandidate(candidateEntity.getIdCandidate()).get(0).getName()).isEqualTo(Language.JS);
        assertThat(technologyService.listAllTechnologiesAssignToCandidate(candidateEntity.getIdCandidate())).isNotNull();

    }
}