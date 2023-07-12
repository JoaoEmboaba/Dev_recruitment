package com.senior.devrecruitment.entities.candidato.DTO;

import com.senior.devrecruitment.entities.candidato.Candidato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;
import java.time.LocalDate;

public record DTOListagemCandidatos(
        @NotBlank String nome,
        @NotBlank String nacionalidade,
        @NotNull LocalDate data_nascimento) {

    public DTOListagemCandidatos(Candidato candidato) {
        this(candidato.getNome(), candidato.getNacionalidade(), candidato.getDataNascimento());
    }
}
