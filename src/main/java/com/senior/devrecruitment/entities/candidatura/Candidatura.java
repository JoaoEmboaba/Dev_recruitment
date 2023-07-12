package com.senior.devrecruitment.entities.candidatura;

import com.senior.devrecruitment.entities.candidato.Candidato;
import com.senior.devrecruitment.entities.vaga.Vaga;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "Candidatura")
@Table(name = "candidatura")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_candidatura")
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_candidatura;
    private LocalDate data_candidatura;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCandidato", referencedColumnName = "idCandidato")
    private Candidato candidato;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idVaga")
    private Vaga vaga;

    public Candidatura(@Valid Candidato candidato, @Valid Vaga vaga) {
        this.candidato = candidato;
        this.vaga = vaga;
        this.data_candidatura = LocalDate.now();
    }
}
