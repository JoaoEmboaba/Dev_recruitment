package com.senior.devrecruitment.entities.candidacy;

import com.senior.devrecruitment.entities.candidate.CandidateEntity;
import com.senior.devrecruitment.entities.vacancy.VacancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface CandidacyRepository extends JpaRepository<CandidacyEntity, UUID> {

    @Query("select c " +
            "from my_domain.my_service.CandidateEntity c " +
            "inner join c.technologies t " +
            "where t.recommendedArea in (select v.needVacancy from my_domain.my_service.VacancyEntity v where v.id = ?1)")
    List<CandidateEntity> listCandidatesWhoAreSuitableForThatVacancy(UUID idVaga);

    @Query("select c " +
            "from my_domain.my_service.CandidateEntity c " +
            "inner join c.technologies t " +
            "where t.recommendedArea in (select v.needVacancy from my_domain.my_service.VacancyEntity v where v.id = ?1) and c.hired = false ")
    List<CandidateEntity> listCandidatesWhoAreSuitableForThatVacancyAndAreNotHired(UUID idVaga);

    @Query("select case when COUNT(c) > 0 THEN true ELSE false END " +
            "from my_domain.my_service.CandidateEntity c " +
            "inner join c.technologies t " +
            "where t.recommendedArea in (select v.needVacancy from my_domain.my_service.VacancyEntity v where v.id = ?1) " +
            "AND c.id = ?2")
    boolean compareVacancyNeedWithCandidateTech(UUID idVaga, UUID idCandidate);

    @Query("select case when COUNT(c) > 0 THEN true ELSE false END " +
            "from my_domain.my_service.CandidacyEntity c " +
            "where c.candidates = ?1 AND c.vacancies = ?2")
    boolean existsByCandidatesAndVacancies(CandidateEntity candidateEntity, VacancyEntity vacancyEntity);

}

