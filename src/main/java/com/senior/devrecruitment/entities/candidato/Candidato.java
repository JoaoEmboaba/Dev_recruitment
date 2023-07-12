package com.senior.devrecruitment.entities.candidato;

import com.senior.devrecruitment.entities.candidato.DTO.DTOCandidato;
import com.senior.devrecruitment.entities.candidatura.Candidatura;
import com.senior.devrecruitment.entities.tecnologia.Tecnologia;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Candidato")
@Table(name = "candidato")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "idCandidato")

public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCandidato;
    private String nome;
    private String nacionalidade;
    private LocalDate dataNascimento;
    private boolean contratado;
    private boolean ativo = true;

    @ManyToMany
    @JoinTable(name = "candidato_tecnologia", joinColumns = @JoinColumn(name = "idCandidato"), inverseJoinColumns = @JoinColumn(name = "id_tech"))
    private List<Tecnologia> tecnologiasCandidatoes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "candidatura", joinColumns = @JoinColumn(name = "idCandidato"), inverseJoinColumns = @JoinColumn(name = "idVaga"))
    private List<Candidatura> Candidaturas = new ArrayList<>();

    public Candidato(DTOCandidato candidato) {
        this.idCandidato = UUID.randomUUID();
        this.nome = candidato.nome();
        this.nacionalidade = candidato.nacionalidade();
        this.dataNascimento = candidato.data_nascimento();
        this.contratado = candidato.contratado();
    }
}
