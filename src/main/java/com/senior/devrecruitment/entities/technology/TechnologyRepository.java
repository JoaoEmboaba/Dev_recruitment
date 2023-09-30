package com.senior.devrecruitment.entities.technology;

import com.senior.devrecruitment.enums.Framework;
import com.senior.devrecruitment.enums.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface TechnologyRepository extends JpaRepository<TechnologyEntity, UUID> {

    @Query("SELECT CASE WHEN COUNT(t) Technology t WHEN t.name = language AND t.framework = framework")
    boolean technologyExists(Language language, Framework framework);

    @Query(value = "select t.* " +
            "from tecnologia t " +
            "inner join candidato_tecnologia ct ON ct.id_tech = t.id_tech " +
            "inner join candidato c on c.id_candidato = ct.id_candidato " +
            "where c.id_candidato = :id", nativeQuery = true)
    List<TechnologyProjection> listAllTechnologiesAssignToCandidate(UUID id);

}
