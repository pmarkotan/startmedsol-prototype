package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.CsProcedure} entity.
 */
public class CsProcedureDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 10)
    private String code;

    @NotNull
    private String description;


    private Long validityId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getValidityId() {
        return validityId;
    }

    public void setValidityId(Long validityId) {
        this.validityId = validityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CsProcedureDTO)) {
            return false;
        }

        return id != null && id.equals(((CsProcedureDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CsProcedureDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", validityId=" + getValidityId() +
            "}";
    }
}
