package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A PphMedicineQualifiedName.
 */
@Entity
@Table(name = "pph_medicine_qualified_name")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PphMedicineQualifiedName implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 250)
    @Column(name = "name", length = 250)
    private String name;

    @Size(max = 128)
    @Column(name = "active_substance", length = 128)
    private String activeSubstance;

    @NotNull
    @Column(name = "active_pupha_data", nullable = false)
    private Boolean activePuphaData;

    @OneToMany(mappedBy = "qualifiedName")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PphMedicine> medicines = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public PphMedicineQualifiedName name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public PphMedicineQualifiedName activeSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
        return this;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public PphMedicineQualifiedName activePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
        return this;
    }

    public void setActivePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
    }

    public Set<PphMedicine> getMedicines() {
        return medicines;
    }

    public PphMedicineQualifiedName medicines(Set<PphMedicine> pphMedicines) {
        this.medicines = pphMedicines;
        return this;
    }

    public PphMedicineQualifiedName addMedicine(PphMedicine pphMedicine) {
        this.medicines.add(pphMedicine);
        pphMedicine.setQualifiedName(this);
        return this;
    }

    public PphMedicineQualifiedName removeMedicine(PphMedicine pphMedicine) {
        this.medicines.remove(pphMedicine);
        pphMedicine.setQualifiedName(null);
        return this;
    }

    public void setMedicines(Set<PphMedicine> pphMedicines) {
        this.medicines = pphMedicines;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PphMedicineQualifiedName)) {
            return false;
        }
        return id != null && id.equals(((PphMedicineQualifiedName) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphMedicineQualifiedName{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", activeSubstance='" + getActiveSubstance() + "'" +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            "}";
    }
}
