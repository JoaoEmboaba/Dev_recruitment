package com.senior.devrecruitment.entities.vacancy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface VacancyRepository extends JpaRepository<VacancyEntity, UUID> {
}
