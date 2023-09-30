package com.senior.devrecruitment.entities.candidate.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

public record DTOCandidate(

        @NotBlank String name, @NotBlank String nacionality, @NotNull LocalDate birthDate, @NotNull boolean hired) {
}
