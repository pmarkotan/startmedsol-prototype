package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PerformedProcedure.
 */
@Entity
@Table(name = "performed_procedure")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PerformedProcedure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "performedProcedures", allowSetters = true)
    private CsProcedure procedure;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "performedProcedures", allowSetters = true)
    private MedicalCase medicalCase;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CsProcedure getProcedure() {
        return procedure;
    }

    public PerformedProcedure procedure(CsProcedure csProcedure) {
        this.procedure = csProcedure;
        return this;
    }

    public void setProcedure(CsProcedure csProcedure) {
        this.procedure = csProcedure;
    }

    public MedicalCase getMedicalCase() {
        return medicalCase;
    }

    public PerformedProcedure medicalCase(MedicalCase medicalCase) {
        this.medicalCase = medicalCase;
        return this;
    }

    public void setMedicalCase(MedicalCase medicalCase) {
        this.medicalCase = medicalCase;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PerformedProcedure)) {
            return false;
        }
        return id != null && id.equals(((PerformedProcedure) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PerformedProcedure{" +
            "id=" + getId() +
            "}";
    }
}
