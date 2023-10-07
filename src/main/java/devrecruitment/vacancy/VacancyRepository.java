package devrecruitment.vacancy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface VacancyRepository extends JpaRepository<Vacancy, UUID> {

    List<Vacancy> findAllByActiveIsTrue();

    List<Vacancy> findAllByActiveIsFalse();
}
