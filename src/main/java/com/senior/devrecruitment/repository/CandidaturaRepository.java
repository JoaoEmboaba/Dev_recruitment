package com.senior.devrecruitment.repository;

import com.senior.devrecruitment.entities.candidatura.Candidatura;
import com.senior.devrecruitment.projection.CandidaturaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, UUID> {

//    List<CandidaturaProjection> buscarCandidatosPelaVaga(@Param("idVaga") UUID idVaga);
}

