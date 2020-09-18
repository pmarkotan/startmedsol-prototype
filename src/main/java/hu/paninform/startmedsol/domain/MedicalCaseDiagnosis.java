package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MedicalCaseDiagnosis.
 */
@Entity
@Table(name = "medical_case_diagnosis")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MedicalCaseDiagnosis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "medicalCaseDiagnoses", allowSetters = true)
    private CsDiagnosis diagnosis;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "medicalCaseDiagnoses", allowSetters = true)
    private MedicalCase medicalCase;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CsDiagnosis getDiagnosis() {
        return diagnosis;
    }

    public MedicalCaseDiagnosis diagnosis(CsDiagnosis csDiagnosis) {
        this.diagnosis = csDiagnosis;
        return this;
    }

    public void setDiagnosis(CsDiagnosis csDiagnosis) {
        this.diagnosis = csDiagnosis;
    }

    public MedicalCase getMedicalCase() {
        return medicalCase;
    }

    public MedicalCaseDiagnosis medicalCase(MedicalCase medicalCase) {
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
        if (!(o instanceof MedicalCaseDiagnosis)) {
            return false;
        }
        return id != null && id.equals(((MedicalCaseDiagnosis) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalCaseDiagnosis{" +
            "id=" + getId() +
            "}";
    }
}
