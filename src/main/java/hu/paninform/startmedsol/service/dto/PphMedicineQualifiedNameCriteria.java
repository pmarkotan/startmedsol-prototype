package hu.paninform.startmedsol.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link hu.paninform.startmedsol.domain.PphMedicineQualifiedName} entity. This class is used
 * in {@link hu.paninform.startmedsol.web.rest.PphMedicineQualifiedNameResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /pph-medicine-qualified-names?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PphMedicineQualifiedNameCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter activeSubstance;

    private BooleanFilter activePuphaData;

    private LongFilter medicineId;

    public PphMedicineQualifiedNameCriteria() {
    }

    public PphMedicineQualifiedNameCriteria(PphMedicineQualifiedNameCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.activeSubstance = other.activeSubstance == null ? null : other.activeSubstance.copy();
        this.activePuphaData = other.activePuphaData == null ? null : other.activePuphaData.copy();
        this.medicineId = other.medicineId == null ? null : other.medicineId.copy();
    }

    @Override
    public PphMedicineQualifiedNameCriteria copy() {
        return new PphMedicineQualifiedNameCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getActiveSubstance() {
        return activeSubstance;
    }

    public void setActiveSubstance(StringFilter activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public BooleanFilter getActivePuphaData() {
        return activePuphaData;
    }

    public void setActivePuphaData(BooleanFilter activePuphaData) {
        this.activePuphaData = activePuphaData;
    }

    public LongFilter getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(LongFilter medicineId) {
        this.medicineId = medicineId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PphMedicineQualifiedNameCriteria that = (PphMedicineQualifiedNameCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(activeSubstance, that.activeSubstance) &&
            Objects.equals(activePuphaData, that.activePuphaData) &&
            Objects.equals(medicineId, that.medicineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        activeSubstance,
        activePuphaData,
        medicineId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphMedicineQualifiedNameCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (activeSubstance != null ? "activeSubstance=" + activeSubstance + ", " : "") +
                (activePuphaData != null ? "activePuphaData=" + activePuphaData + ", " : "") +
                (medicineId != null ? "medicineId=" + medicineId + ", " : "") +
            "}";
    }

}
