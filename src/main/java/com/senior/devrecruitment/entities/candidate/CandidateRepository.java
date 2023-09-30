package com.senior.devrecruitment.entities.candidate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Page<CandidateEntity> findAllByContratadoTrue(Pageable page);

    Page<CandidateEntity> findAllByContratadoFalse(Pageable page);

}
