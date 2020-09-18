package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.PerformedProcedure} entity.
 */
public class PerformedProcedureDTO implements Serializable {
    
    private Long id;


    private Long procedureId;

    private Long medicalCaseId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long csProcedureId) {
        this.procedureId = csProcedureId;
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
        if (!(o instanceof PerformedProcedureDTO)) {
            return false;
        }

        return id != null && id.equals(((PerformedProcedureDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PerformedProcedureDTO{" +
            "id=" + getId() +
            ", procedureId=" + getProcedureId() +
            ", medicalCaseId=" + getMedicalCaseId() +
            "}";
    }
}
