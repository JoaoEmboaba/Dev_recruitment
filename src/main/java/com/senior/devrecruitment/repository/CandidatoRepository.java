package com.senior.devrecruitment.repository;

import com.senior.devrecruitment.entities.candidato.Candidato;
import com.senior.devrecruitment.projection.TecnologiaProjection;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, UUID> {
    Page<Candidato> findAllByContratadoTrue(Pageable page);

    Page<Candidato> findAllByContratadoFalse(Pageable page);

    @Query(value = "select t.* " +
            "from tecnologia t " +
            "inner join candidato_tecnologia ct ON ct.id_tech = t.id_tech " +
            "inner join candidato c on c.id_candidato = ct.id_candidato " +
            "where c.id_candidato = :id", nativeQuery = true)
    List<TecnologiaProjection> findTecnologiasByIdCandidato(UUID id);
    Page<Candidato> findByNome(String nome, Pageable page);

}
