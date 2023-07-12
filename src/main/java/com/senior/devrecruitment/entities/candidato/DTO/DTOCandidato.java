package com.senior.devrecruitment.entities.candidato.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;
import java.util.UUID;
import java.time.LocalDate;

public record DTOCandidato(

        @NotBlank String nome,
        @NotBlank String nacionalidade,
        @NotNull LocalDate data_nascimento,
        @NotNull boolean contratado) {
}
