package hu.paninform.startmedsol.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.DataGenerator} entity.
 */
public class DataGeneratorDTO implements Serializable {
    
    private Long id;

    private Integer provider;

    private Integer praxis;

    private Integer patient;

    private Integer medicalCase;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProvider() {
        return provider;
    }

    public void setProvider(Integer provider) {
        this.provider = provider;
    }

    public Integer getPraxis() {
        return praxis;
    }

    public void setPraxis(Integer praxis) {
        this.praxis = praxis;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    public Integer getMedicalCase() {
        return medicalCase;
    }

    public void setMedicalCase(Integer medicalCase) {
        this.medicalCase = medicalCase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataGeneratorDTO)) {
            return false;
        }

        return id != null && id.equals(((DataGeneratorDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DataGeneratorDTO{" +
            "id=" + getId() +
            ", provider=" + getProvider() +
            ", praxis=" + getPraxis() +
            ", patient=" + getPatient() +
            ", medicalCase=" + getMedicalCase() +
            "}";
    }
}
