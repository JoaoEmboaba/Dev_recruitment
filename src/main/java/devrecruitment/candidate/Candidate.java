
package devrecruitment.candidate;

import devrecruitment.candidacy.Candidacy;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;
import devrecruitment.technology.Technology;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity(name="my_domain.my_service.CandidateEntity")
@Table(name="candidate")
@Getter
public class Candidate implements Persistable<java.util.UUID> {

    /**
     * Id do candidato
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false)
    private java.util.UUID id;

    /**
     * Nome do candidato
     */
    @Column(name = "name", length = 255)
    private String name;

    /**
     * Nacionalidade do candidato
     */
    @Column(name = "candidate_nationality", length = 20)
    private String candidateNationality;

    /**
     * Data de nascimento do candidato
     */
    @Column(name = "birth_date")
    private java.time.LocalDate birthDate;

    /**
     * Status da contratação do candidato
     */
    @Column(name = "hired")
    private Boolean hired;

    /**
     * Campo para realizar a exclusão lógica
     */
    @Column(name = "active")
    private Boolean active;

    /**
     * Campo para realizar o relacionamento entre a tecnologia
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "candidate_technologies",
            joinColumns = @JoinColumn(name = "candidate_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "technologies_id", referencedColumnName = "id"))
    private java.util.Set<Technology> technologies = new java.util.LinkedHashSet<>();

    /**
     * Campo para realizar o relacionamento com a tabela candidatura
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidates")
    private final java.util.List<Candidacy> candidacies = new ArrayList<>();

    public java.util.UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    public String getName() {
        return name;
    }

    public String getCandidateNationality() {
        return candidateNationality;
    }

    public java.time.LocalDate getBirthDate() {
        return birthDate;
    }

    public Boolean getHired() {
        return hired;
    }

    public Boolean getActive() {
        return active;
    }

    public java.util.Set<Technology> getTechnologies() {
        return technologies;
    }

    public java.util.List<Candidacy> getCandidacies() {
        return candidacies;
    }

    public void setId(java.util.UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCandidateNationality(String candidateNationality) {
        this.candidateNationality = candidateNationality;
    }

    public void setBirthDate(java.time.LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setHired(Boolean hired) {
        this.hired = hired;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void forceSetTechnologies(List<Technology> technologies){
        this.technologies = new java.util.HashSet<>(technologies);
    }

    public void setTechnologies(List<Technology> technologies) {
        if (technologies != null) {
            technologies.forEach(this::addToTechnologies);
        } else {
            final java.util.Set<Technology> current = new java.util.LinkedHashSet<Technology>(this.technologies);
            current.forEach(this::removeFromTechnologies);
        }
    }

    public void addToTechnologies(Technology technology) {
        if (technology.getId() == null || !technologies.contains(technology)) {
            technologies.add(technology);
            technology.getCandidates().add(this);
        }
    }

    public Technology getFromTechnologies(java.util.UUID technologyEntityId) {
        Optional<Technology> entity = technologies.stream().filter(e -> e.getId().equals(technologyEntityId)).findFirst();
        return entity.orElse(null);
    }

    public void removeFromTechnologies(Technology technology) {
        technologies.remove(technology);
        technology.getCandidates().remove(this);
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
            candidacy.setCandidates(this);
        } else {
            //required for merge operations
            candidacies.remove(candidacy);
            candidacies.add(candidacy);
            candidacy.setCandidates(this);
        }
    }

    public Candidacy getFromCandidacies(java.util.UUID candidacyEntityId) {
        Optional<Candidacy> entity = candidacies.stream().filter(e -> e.getId().equals(candidacyEntityId)).findFirst();
        return entity.orElse(null);
    }

    public void removeFromCandidacies(Candidacy candidacy) {
        candidacies.remove(candidacy);
        candidacy.setCandidates(null);
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
        if (!(obj instanceof Candidate other)) {
            return false;
        }
        if (id == null) {
            return false;
        }
        return id.equals(other.id);
    }
}
