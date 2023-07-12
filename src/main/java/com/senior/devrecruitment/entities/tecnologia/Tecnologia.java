package com.senior.devrecruitment.entities.tecnologia;

import com.senior.devrecruitment.entities.candidato.Candidato;
import com.senior.devrecruitment.entities.tecnologia.DTO.DTOTecnologia;
import com.senior.devrecruitment.entities.vaga.Vaga;
import com.senior.devrecruitment.enums.AreaAconselhavel;
import com.senior.devrecruitment.enums.Framework;
import com.senior.devrecruitment.enums.Linguagens;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "tecnologia")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idTech")
@Getter
@Setter
@Builder
public class Tecnologia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idTech;

    @Enumerated(EnumType.STRING)
    private Linguagens nome;

    @Enumerated(EnumType.STRING)
    private Framework framework;

    @Enumerated(EnumType.STRING)
    private List<AreaAconselhavel> areaaconselhavel;

    private String descricao;

    private boolean ativo = true;

    @ManyToMany(mappedBy = "tecnologiasVagases")
    private List<Vaga> vagases;

    @ManyToMany(mappedBy = "tecnologiasCandidatoes")
    private List<Candidato> candidatoes;

    public Tecnologia(DTOTecnologia tecnologia, Candidato candidato) {
        this.setCandidatoes(new ArrayList<>());
        this.getCandidatoes().add(candidato);
        this.nome = tecnologia.nome();
        this.framework = tecnologia.framework();
        this.descricao = tecnologia.descricao();
    }

    public Tecnologia(DTOTecnologia tecnologia) {
        this.idTech = UUID.randomUUID();
        this.nome = tecnologia.nome();
        this.framework = tecnologia.framework();
        this.descricao = tecnologia.descricao();
    }
}
