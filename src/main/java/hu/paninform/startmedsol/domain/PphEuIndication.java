package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PphEuIndication.
 */
@Entity
@Table(name = "pph_eu_indication")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PphEuIndication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "external_id")
    private Integer externalId;

    @Column(name = "indication_no")
    private Integer indicationNo;

    @Size(max = 4000)
    @Column(name = "description", length = 4000)
    private String description;

    @NotNull
    @Column(name = "active_pupha_data", nullable = false)
    private Boolean activePuphaData;

    @ManyToOne
    @JsonIgnoreProperties(value = "euIndications", allowSetters = true)
    private PphEuScore euScore;

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

    public PphEuIndication externalId(Integer externalId) {
        this.externalId = externalId;
        return this;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    public Integer getIndicationNo() {
        return indicationNo;
    }

    public PphEuIndication indicationNo(Integer indicationNo) {
        this.indicationNo = indicationNo;
        return this;
    }

    public void setIndicationNo(Integer indicationNo) {
        this.indicationNo = indicationNo;
    }

    public String getDescription() {
        return description;
    }

    public PphEuIndication description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public PphEuIndication activePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
        return this;
    }

    public void setActivePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
    }

    public PphEuScore getEuScore() {
        return euScore;
    }

    public PphEuIndication euScore(PphEuScore pphEuScore) {
        this.euScore = pphEuScore;
        return this;
    }

    public void setEuScore(PphEuScore pphEuScore) {
        this.euScore = pphEuScore;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PphEuIndication)) {
            return false;
        }
        return id != null && id.equals(((PphEuIndication) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphEuIndication{" +
            "id=" + getId() +
            ", externalId=" + getExternalId() +
            ", indicationNo=" + getIndicationNo() +
            ", description='" + getDescription() + "'" +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            "}";
    }
}
