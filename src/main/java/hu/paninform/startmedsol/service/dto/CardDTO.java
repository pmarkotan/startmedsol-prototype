package hu.paninform.startmedsol.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import hu.paninform.startmedsol.domain.enumeration.IdDocType;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.Card} entity.
 */
public class CardDTO implements Serializable {
    
    private Long id;

    @NotNull
    private IdDocType type;

    @NotNull
    @Size(max = 32)
    private String identifier;

    private LocalDate validTo;

    private Boolean primary;


    private Long patientId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IdDocType getType() {
        return type;
    }

    public void setType(IdDocType type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public Boolean isPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CardDTO)) {
            return false;
        }

        return id != null && id.equals(((CardDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CardDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", identifier='" + getIdentifier() + "'" +
            ", validTo='" + getValidTo() + "'" +
            ", primary='" + isPrimary() + "'" +
            ", patientId=" + getPatientId() +
            "}";
    }
}
