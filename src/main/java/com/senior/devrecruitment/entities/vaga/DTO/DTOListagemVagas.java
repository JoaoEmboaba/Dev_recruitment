package com.senior.devrecruitment.entities.vaga.DTO;

import com.senior.devrecruitment.entities.vaga.Vaga;
import com.senior.devrecruitment.enums.NecessidadeVaga;
import jakarta.validation.constraints.NotBlank;

public record DTOListagemVagas(@NotBlank String descricao, @NotBlank NecessidadeVaga necessidadeVaga) {

    public DTOListagemVagas(Vaga vaga) {
        this(vaga.getDescricao(), vaga.getNecessidadevaga());
    }
}
