package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DataGenerator.
 */
@Entity
@Table(name = "data_generator")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DataGenerator implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "provider")
    private Integer provider;

    @Column(name = "praxis")
    private Integer praxis;

    @Column(name = "patient")
    private Integer patient;

    @Column(name = "medical_case")
    private Integer medicalCase;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProvider() {
        return provider;
    }

    public DataGenerator provider(Integer provider) {
        this.provider = provider;
        return this;
    }

    public void setProvider(Integer provider) {
        this.provider = provider;
    }

    public Integer getPraxis() {
        return praxis;
    }

    public DataGenerator praxis(Integer praxis) {
        this.praxis = praxis;
        return this;
    }

    public void setPraxis(Integer praxis) {
        this.praxis = praxis;
    }

    public Integer getPatient() {
        return patient;
    }

    public DataGenerator patient(Integer patient) {
        this.patient = patient;
        return this;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    public Integer getMedicalCase() {
        return medicalCase;
    }

    public DataGenerator medicalCase(Integer medicalCase) {
        this.medicalCase = medicalCase;
        return this;
    }

    public void setMedicalCase(Integer medicalCase) {
        this.medicalCase = medicalCase;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataGenerator)) {
            return false;
        }
        return id != null && id.equals(((DataGenerator) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DataGenerator{" +
            "id=" + getId() +
            ", provider=" + getProvider() +
            ", praxis=" + getPraxis() +
            ", patient=" + getPatient() +
            ", medicalCase=" + getMedicalCase() +
            "}";
    }
}
