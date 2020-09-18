package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.MedicalCaseDiagnosis} entity.
 */
public class MedicalCaseDiagnosisDTO implements Serializable {
    
    private Long id;


    private Long diagnosisId;

    private Long medicalCaseId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Long csDiagnosisId) {
        this.diagnosisId = csDiagnosisId;
    }

    public Long getMedicalCaseId() {
        return medicalCaseId;
    }

    public void setMedicalCaseId(Long medicalCaseId) {
        this.medicalCaseId = medicalCaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalCaseDiagnosisDTO)) {
            return false;
        }

        return id != null && id.equals(((MedicalCaseDiagnosisDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalCaseDiagnosisDTO{" +
            "id=" + getId() +
            ", diagnosisId=" + getDiagnosisId() +
            ", medicalCaseId=" + getMedicalCaseId() +
            "}";
    }
}
