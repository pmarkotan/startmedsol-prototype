package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.PphMedicineQualifiedName} entity.
 */
public class PphMedicineQualifiedNameDTO implements Serializable {
    
    private Long id;

    @Size(max = 250)
    private String name;

    @Size(max = 128)
    private String activeSubstance;

    @NotNull
    private Boolean activePuphaData;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public void setActivePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PphMedicineQualifiedNameDTO)) {
            return false;
        }

        return id != null && id.equals(((PphMedicineQualifiedNameDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphMedicineQualifiedNameDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", activeSubstance='" + getActiveSubstance() + "'" +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            "}";
    }
}
