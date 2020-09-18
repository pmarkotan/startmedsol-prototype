package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;
import hu.paninform.startmedsol.domain.enumeration.PatientDocumentationType;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.CaseTextDocumentation} entity.
 */
public class CaseTextDocumentationDTO implements Serializable {
    
    private Long id;

    @Lob
    private String text;

    @NotNull
    private PatientDocumentationType type;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PatientDocumentationType getType() {
        return type;
    }

    public void setType(PatientDocumentationType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CaseTextDocumentationDTO)) {
            return false;
        }

        return id != null && id.equals(((CaseTextDocumentationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CaseTextDocumentationDTO{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
