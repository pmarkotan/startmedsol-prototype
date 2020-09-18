package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PphNiche.
 */
@Entity
@Table(name = "pph_niche")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PphNiche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "external_id")
    private Integer externalId;

    @Column(name = "equivalence_group_id")
    private Integer equivalenceGroupId;

    @Size(max = 256)
    @Column(name = "equivalent_medicine", length = 256)
    private String equivalentMedicine;

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

    public Integer getExternalId() {
        return externalId;
    }

    public PphNiche externalId(Integer externalId) {
        this.externalId = externalId;
        return this;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    public Integer getEquivalenceGroupId() {
        return equivalenceGroupId;
    }

    public PphNiche equivalenceGroupId(Integer equivalenceGroupId) {
        this.equivalenceGroupId = equivalenceGroupId;
        return this;
    }

    public void setEquivalenceGroupId(Integer equivalenceGroupId) {
        this.equivalenceGroupId = equivalenceGroupId;
    }

    public String getEquivalentMedicine() {
        return equivalentMedicine;
    }

    public PphNiche equivalentMedicine(String equivalentMedicine) {
        this.equivalentMedicine = equivalentMedicine;
        return this;
    }

    public void setEquivalentMedicine(String equivalentMedicine) {
        this.equivalentMedicine = equivalentMedicine;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public PphNiche activePuphaData(Boolean activePuphaData) {
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
        if (!(o instanceof PphNiche)) {
            return false;
        }
        return id != null && id.equals(((PphNiche) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphNiche{" +
            "id=" + getId() +
            ", externalId=" + getExternalId() +
            ", equivalenceGroupId=" + getEquivalenceGroupId() +
            ", equivalentMedicine='" + getEquivalentMedicine() + "'" +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            "}";
    }
}
