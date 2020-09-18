package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A PphSpecBudget.
 */
@Entity
@Table(name = "pph_spec_budget")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PphSpecBudget implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 256)
    @Column(name = "indication", length = 256)
    private String indication;

    @Size(max = 256)
    @Column(name = "diagnosis_list", length = 256)
    private String diagnosisList;

    @Column(name = "valid_from")
    private Instant validFrom;

    @Column(name = "valid_to")
    private Instant validTo;

    @NotNull
    @Column(name = "active_pupha_data", nullable = false)
    private Boolean activePuphaData;

    @ManyToOne
    @JsonIgnoreProperties(value = "pphSpecBudgets", allowSetters = true)
    private PphMedicine medicine;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndication() {
        return indication;
    }

    public PphSpecBudget indication(String indication) {
        this.indication = indication;
        return this;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getDiagnosisList() {
        return diagnosisList;
    }

    public PphSpecBudget diagnosisList(String diagnosisList) {
        this.diagnosisList = diagnosisList;
        return this;
    }

    public void setDiagnosisList(String diagnosisList) {
        this.diagnosisList = diagnosisList;
    }

    public Instant getValidFrom() {
        return validFrom;
    }

    public PphSpecBudget validFrom(Instant validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    public void setValidFrom(Instant validFrom) {
        this.validFrom = validFrom;
    }

    public Instant getValidTo() {
        return validTo;
    }

    public PphSpecBudget validTo(Instant validTo) {
        this.validTo = validTo;
        return this;
    }

    public void setValidTo(Instant validTo) {
        this.validTo = validTo;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public PphSpecBudget activePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
        return this;
    }

    public void setActivePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
    }

    public PphMedicine getMedicine() {
        return medicine;
    }

    public PphSpecBudget medicine(PphMedicine pphMedicine) {
        this.medicine = pphMedicine;
        return this;
    }

    public void setMedicine(PphMedicine pphMedicine) {
        this.medicine = pphMedicine;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PphSpecBudget)) {
            return false;
        }
        return id != null && id.equals(((PphSpecBudget) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphSpecBudget{" +
            "id=" + getId() +
            ", indication='" + getIndication() + "'" +
            ", diagnosisList='" + getDiagnosisList() + "'" +
            ", validFrom='" + getValidFrom() + "'" +
            ", validTo='" + getValidTo() + "'" +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            "}";
    }
}
