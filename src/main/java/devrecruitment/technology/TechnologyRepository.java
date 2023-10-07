package devrecruitment.technology;

import devrecruitment.enums.Framework;
import devrecruitment.enums.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface TechnologyRepository extends JpaRepository<Technology, UUID> {

    List<Technology> findAllByActiveIsTrue();

    List<Technology> findAllByActiveIsFalse();

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Technology t WHERE t.name = ?1 AND t.framework = ?2")
    boolean technologyExists(Language language, Framework framework);

}