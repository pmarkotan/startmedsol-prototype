package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PphMedicineForm.
 */
@Entity
@Table(name = "pph_medicine_form")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PphMedicineForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "external_id")
    private Long externalId;

    @Size(max = 250)
    @Column(name = "name", length = 250)
    private String name;

    @NotNull
    @Column(name = "active_pupha_data", nullable = false)
    private Boolean activePuphaData;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExternalId() {
        return externalId;
    }

    public PphMedicineForm externalId(Long externalId) {
        this.externalId = externalId;
        return this;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public PphMedicineForm name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public PphMedicineForm activePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
        return this;
    }

    public void setActivePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PphMedicineForm)) {
            return false;
        }
        return id != null && id.equals(((PphMedicineForm) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphMedicineForm{" +
            "id=" + getId() +
            ", externalId=" + getExternalId() +
            ", name='" + getName() + "'" +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            "}";
    }
}
