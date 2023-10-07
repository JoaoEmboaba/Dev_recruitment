package devrecruitment.candidate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import devrecruitment.technology.Technology;

import java.util.List;
import java.util.UUID;

@Repository
interface CandidateRepository extends JpaRepository<Candidate, UUID> {

    List<Candidate> findAllByActiveIsTrue();

    List<Candidate> findAllByActiveIsFalse();

        @Query("select t " +
                "from Candidate c " +
                "inner join c.technologies t " +
                "where c.id = ?1")
    public List<Technology> listarTecnologiasPeloIdDoCandidato(@Param(value = "id") UUID id);

    List<Candidate> getCandidateByName(String name);
}
