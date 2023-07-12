package com.senior.devrecruitment.repository;

import com.senior.devrecruitment.entities.vaga.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, UUID> {
}
