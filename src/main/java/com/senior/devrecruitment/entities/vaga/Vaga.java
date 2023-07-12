package com.senior.devrecruitment.entities.vaga;

import com.senior.devrecruitment.entities.tecnologia.Tecnologia;
import com.senior.devrecruitment.entities.vaga.DTO.DTOVaga;
import com.senior.devrecruitment.enums.NecessidadeVaga;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "Vaga")
@Table(name = "vaga")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_vaga")

public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idVaga;

    private String descricao;

    @Enumerated(value = EnumType.STRING)
    private NecessidadeVaga necessidadevaga;

    private boolean ativo = true;

    @ManyToMany
    @JoinTable(
            name = "vaga_tecnologia",
            joinColumns = @JoinColumn(name = "idVaga"),
            inverseJoinColumns = @JoinColumn(name = "idTech"))
    private List<Tecnologia> tecnologiasVagases;

    public Vaga(DTOVaga vaga) {
        this.descricao = vaga.descricao();
        this.necessidadevaga = vaga.necessidadevaga();
    }
}
