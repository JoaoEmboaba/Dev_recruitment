package devrecruitment.candidacy;

import devrecruitment.candidate.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import devrecruitment.vacancy.Vacancy;

import java.util.List;
import java.util.UUID;

@Repository
interface CandidacyRepository extends JpaRepository<Candidacy, UUID> {

    @Query("select c " +
            "from Candidate c " +
            "inner join c.technologies t " +
            "where t.recommendedArea in (select v.needVacancy from Vacancy v where v.id = ?1)")
    List<Candidate> listCandidatesWhoAreSuitableForThatVacancy(UUID idVaga);

    @Query("select c " +
            "from Candidate c " +
            "inner join c.technologies t " +
            "where t.recommendedArea in (select v.needVacancy Vacancy v where v.id = ?1) and c.hired = false ")
    List<Candidate> listCandidatesWhoAreSuitableForThatVacancyAndAreNotHired(UUID idVaga);

    @Query("select case when COUNT(c) > 0 THEN true ELSE false END " +
            "from Candidate c " +
            "inner join c.technologies t " +
            "where t.recommendedArea in (select v.needVacancy from Vacancy v where v.id = ?1) " +
            "AND c.id = ?2")
    boolean compareVacancyNeedWithCandidateTech(UUID idVaga, UUID idCandidate);

    @Query("select case when COUNT(c) > 0 THEN true ELSE false END " +
            "from Candidacy c " +
            "where c.candidates = ?1 AND c.vacancies = ?2")
    boolean existsByCandidatesAndVacancies(Candidate candidate, Vacancy vacancy);
}
