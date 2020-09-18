package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.ProceduresOfPraxis} entity.
 */
public class ProceduresOfPraxisDTO implements Serializable {
    
    private Long id;


    private Long procedureId;

    private Long praxisId;
    
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

    public Long getPraxisId() {
        return praxisId;
    }

    public void setPraxisId(Long praxisId) {
        this.praxisId = praxisId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProceduresOfPraxisDTO)) {
            return false;
        }

        return id != null && id.equals(((ProceduresOfPraxisDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProceduresOfPraxisDTO{" +
            "id=" + getId() +
            ", procedureId=" + getProcedureId() +
            ", praxisId=" + getPraxisId() +
            "}";
    }
}
