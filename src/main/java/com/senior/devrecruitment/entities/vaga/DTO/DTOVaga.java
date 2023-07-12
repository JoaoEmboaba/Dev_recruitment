package com.senior.devrecruitment.entities.vaga.DTO;

import com.senior.devrecruitment.enums.NecessidadeVaga;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOVaga(@NotBlank String descricao, @NotNull NecessidadeVaga necessidadevaga) {
}
