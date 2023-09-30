package com.senior.devrecruitment.entities.candidate.DTO;

import com.senior.devrecruitment.entities.candidate.CandidateEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DTOListCandidates(
        @NotBlank String name,
        @NotBlank String nacionality,
        @NotNull LocalDate birthDate) {

    public DTOListCandidates(CandidateEntity candidateEntity) {
        this(candidateEntity.getName(), candidateEntity.getNacionality(), candidateEntity.getBirthDate());
    }
}
