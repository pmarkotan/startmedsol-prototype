package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PrescriptionEesztId.
 */
@Entity
@Table(name = "prescription_eeszt_id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PrescriptionEesztId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 36)
    @Column(name = "eeszt_id", length = 36, nullable = false)
    private String eesztId;

    @Column(name = "eeszt_version")
    private Integer eesztVersion;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "prescriptionEesztIds", allowSetters = true)
    private Prescription prescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEesztId() {
        return eesztId;
    }

    public PrescriptionEesztId eesztId(String eesztId) {
        this.eesztId = eesztId;
        return this;
    }

    public void setEesztId(String eesztId) {
        this.eesztId = eesztId;
    }

    public Integer getEesztVersion() {
        return eesztVersion;
    }

    public PrescriptionEesztId eesztVersion(Integer eesztVersion) {
        this.eesztVersion = eesztVersion;
        return this;
    }

    public void setEesztVersion(Integer eesztVersion) {
        this.eesztVersion = eesztVersion;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public PrescriptionEesztId prescription(Prescription prescription) {
        this.prescription = prescription;
        return this;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrescriptionEesztId)) {
            return false;
        }
        return id != null && id.equals(((PrescriptionEesztId) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrescriptionEesztId{" +
            "id=" + getId() +
            ", eesztId='" + getEesztId() + "'" +
            ", eesztVersion=" + getEesztVersion() +
            "}";
    }
}
