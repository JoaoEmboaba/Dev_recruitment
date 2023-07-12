package com.senior.devrecruitment.repository;

import com.senior.devrecruitment.entities.tecnologia.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia, UUID> {
}
