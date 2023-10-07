/**
 * This is a generated file. DO NOT EDIT ANY CODE HERE, YOUR CHANGES WILL BE LOST.
 */
package devrecruitment.technology;

import devrecruitment.candidate.Candidate;
import devrecruitment.enums.Framework;
import devrecruitment.enums.Language;
import devrecruitment.enums.PerformanceArea;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity(name="my_domain.my_service.TechnologyEntity")
@Table(name="technology")
@Getter
public class Technology implements Persistable<java.util.UUID> {

    /**
     * Id da tecnologia
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false)
    private java.util.UUID id;

    /**
     * Nome da tecnologia
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private Language name;

    /**
     * Framework da tecnologia
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "framework")
    private Framework framework;

    /**
     * Area aconselhavel da tecnologia
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "recommended_area")
    private PerformanceArea recommendedArea;

    /**
     * Campo para realizar a exclusão lógica
     */
    @Column(name = "active")
    private Boolean active;

    /**
     * Campo para realizar o relacionamento entre a tabela candidatos
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST}, mappedBy = "technologies")
    private java.util.Set<Candidate> candidates = new java.util.HashSet<>();

    @Transient
    private boolean _newEntity;

    public void setId(java.util.UUID id) {
        this.id = id;
    }

    public void setName(Language name) {
        this.name = name;
    }

    public void setFramework(Framework framework) {
        this.framework = framework;
    }

    public void setRecommendedArea(PerformanceArea recommendedArea) {
        this.recommendedArea = recommendedArea;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void forceSetCandidates(List<Candidate> candidates){
        this.candidates = new java.util.HashSet<>(candidates);
    }

    public void setCandidates(List<Candidate> candidates) {
        if (candidates != null) {
            candidates.forEach(this::addToCandidates);
        } else {
            final java.util.Set<Candidate> current = new java.util.LinkedHashSet<>(this.candidates);
            current.forEach(this::removeFromCandidates);
        }
    }

    public void addToCandidates(Candidate candidate) {
        candidate.addToTechnologies(this);
    }

    public Candidate getFromCandidates(java.util.UUID candidateEntityId) {
        Optional<Candidate> entity = candidates.stream().filter(e -> Objects.equals(e.getId(), candidateEntityId)).findFirst();
        return entity.orElse(null);
    }

    public void removeFromCandidates(Candidate candidate) {
        candidate.removeFromTechnologies(this);
    }

    @Override
    public boolean isNew() {
        return _newEntity;
    }

    public void defineAsNewEntity() {
        this._newEntity = true;
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
        if (!(obj instanceof Technology)) {
            return false;
        }
        Technology other = (Technology) obj;
        if (id == null) {
            return false;
        }
        return id.equals(other.id);
    }
}
