/**
 * This is a generated file. DO NOT EDIT ANY CODE HERE, YOUR CHANGES WILL BE LOST.
 */
package devrecruitment.vacancy;

import devrecruitment.candidacy.Candidacy;
import devrecruitment.enums.PerformanceArea;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity(name="my_domain.my_service.VacancyEntity")
@Table(name="vacancy")
@Getter
public class Vacancy implements Persistable<java.util.UUID> {

    /**
     * Id da vaga
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2")
    @Column(name = "id", updatable = false)
    private java.util.UUID id;

    /**
     * descrição da vaga
     */
    @Column(name = "description", length = 100)
    private String description;

    /**
     * Campo para a necessidade da vaga
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "need_vacancy")
    private PerformanceArea needVacancy;

    /**
     * Campo para realizar a exclusão lógica da vaga
     */
    @Column(name = "active")
    private Boolean active;

    /**
     * Campo para fazer a relação entre vaga e candidatura
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vacancies")
    private final java.util.List<Candidacy> candidacies = new ArrayList<>();

    public void setId(java.util.UUID id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNeedVacancy(PerformanceArea needVacancy) {
        this.needVacancy = needVacancy;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setCandidacies(List<Candidacy> candidacies) {
        if (candidacies != null) {
            candidacies.forEach(this::addToCandidacies);
        } else {
            final List<Candidacy> current = new ArrayList<>(this.candidacies);
            current.forEach(this::removeFromCandidacies);
        }
    }

    public void addToCandidacies(Candidacy candidacy) {
        if (candidacy.getId() == null || !candidacies.contains(candidacy)) {
            candidacies.add(candidacy);
            candidacy.setVacancies(this);
        } else {
            //required for merge operations
            candidacies.remove(candidacy);
            candidacies.add(candidacy);
            candidacy.setVacancies(Vacancy.this);
        }
    }

    public Candidacy getFromCandidacies(java.util.UUID candidacyEntityId) {
        Optional<Candidacy> entity = candidacies.stream().filter(e -> e.getId().equals(candidacyEntityId)).findFirst();
        return entity.orElse(null);
    }

    public void removeFromCandidacies(Candidacy candidacy) {
        candidacies.remove(candidacy);
        candidacy.setVacancies(null);
    }

    @Override
    public int hashCode() {
        int ret = 1;
        if (id != null) {
            ret = 31 * ret + id.hashCode();
        }
        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Vacancy other)) {
            return false;
        }
        if (id == null) {
            return false;
        }
        return (id == null) || id.equals(other.id);
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
