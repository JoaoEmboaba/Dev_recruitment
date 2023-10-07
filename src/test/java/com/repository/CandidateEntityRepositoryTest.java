//package com.senior.devrecruitment.repository;
//
//import devrecruitment.candidate.CandidateEntity;
//import devrecruitment.candidate.CandidateService;
//import devrecruitment.enums.Framework;
//import devrecruitment.enums.Language;
//import devrecruitment.enums.PerformanceArea;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.ActiveProfiles;
//import devrecruitment.technology.TechnologyService;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
//class CandidateEntityRepositoryTest {
//
//    @Autowired
//    private TestEntityManager testEntityManager;
//    @Autowired
//    private CandidateService candidateService;
//    @Autowired
//    private TechnologyService technologyService;
//
//    @Test
//    void findTecnologiasByIdCandidato() {
//
//        var candidateEntity = candidateService.(CandidateEntity.builder().name("João").nacionality("Brasileiro").birthDate(LocalDate.now()).hired(true).build());
//
//        TechnologyEntity technologyEntity1 = TechnologyEntity.builder().name(Language.JS).framework(Framework.EXPRESS).performanceArea(List.of(PerformanceArea.BACKEND)).description("Linguagem de tipagem fraca").candidateEntities(List.of(candidateEntity)).build();
//        technologyService.createTechnology(technologyEntity1);
//
//        TechnologyEntity technologyEntity2 = TechnologyEntity.builder().name(Language.JS).framework(Framework.EXPRESS).performanceArea(List.of(PerformanceArea.BIGDATA)).description("Linguagem de tipagem fraca").candidateEntities(List.of(candidateEntity)).build();
//        technologyService.createTechnology(technologyEntity2);
//
//        candidateEntity.setTechnologies(List.of(technologyEntity1, technologyEntity2));
//        technologyEntity1.setCandidateEntities(List.of(candidateEntity));
//        technologyEntity2.setCandidateEntities(List.of(candidateEntity));
//
//        assertThat(technologyService.listAllTechnologiesAssignToCandidate(candidateEntity.getIdCandidate()).get(0).getName()).isEqualTo(Language.JS);
//        assertThat(technologyService.listAllTechnologiesAssignToCandidate(candidateEntity.getIdCandidate())).isNotNull();
//
//    }
//}