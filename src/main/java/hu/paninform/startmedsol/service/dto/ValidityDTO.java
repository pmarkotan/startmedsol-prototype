package hu.paninform.startmedsol.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.Validity} entity.
 */
public class ValidityDTO implements Serializable {
    
    private Long id;

    private LocalDate validFrom;

    private LocalDate validTo;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ValidityDTO)) {
            return false;
        }

        return id != null && id.equals(((ValidityDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ValidityDTO{" +
            "id=" + getId() +
            ", validFrom='" + getValidFrom() + "'" +
            ", validTo='" + getValidTo() + "'" +
            "}";
    }
}
