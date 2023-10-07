/**
 * This is a generated file. DO NOT EDIT ANY CODE HERE, YOUR CHANGES WILL BE LOST.
 */
package devrecruitment.candidacy;

import devrecruitment.candidate.Candidate;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;
import devrecruitment.vacancy.Vacancy;


@Entity(name="my_domain.my_service.CandidacyEntity")
@Table(name="candidacy")
@Getter
public class Candidacy implements Persistable<java.util.UUID> {

    /**
     * Id da candidatura
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false)
    private java.util.UUID id;

    /**
     * data da candidatura
     */
    @Column(name = "candidacy_date")
    private java.time.Instant candidacyDate;

    /**
     * Referência da entidade mestre devrecruitment.candidate
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidates")
    private Candidate candidates;

    /**
     * Referência da entidade mestre devrecruitment.vacancy
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vacancies")
    private Vacancy vacancies;

    @Transient
    private boolean _newEntity;

    public void setId(java.util.UUID id) {
        this.id = id;
    }

    public void setCandidacyDate(java.time.Instant candidacyDate) {
        this.candidacyDate = candidacyDate;
    }

    public void setCandidates(Candidate candidates) {
        this.candidates = candidates;
    }

    public void setVacancies(Vacancy vacancies) {
        this.vacancies = vacancies;
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
        if (!(obj instanceof Candidacy)) {
            return false;
        }
        Candidacy other = (Candidacy) obj;
        if (id == null) {
            return false;
        }
        if ((id != null) && !id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
