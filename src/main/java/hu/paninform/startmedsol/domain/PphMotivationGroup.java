package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PphMotivationGroup.
 */
@Entity
@Table(name = "pph_motivation_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PphMotivationGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private Integer code;

    @Column(name = "target_value")
    private Double targetValue;

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

    public Integer getCode() {
        return code;
    }

    public PphMotivationGroup code(Integer code) {
        this.code = code;
        return this;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Double getTargetValue() {
        return targetValue;
    }

    public PphMotivationGroup targetValue(Double targetValue) {
        this.targetValue = targetValue;
        return this;
    }

    public void setTargetValue(Double targetValue) {
        this.targetValue = targetValue;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public PphMotivationGroup activePuphaData(Boolean activePuphaData) {
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
        if (!(o instanceof PphMotivationGroup)) {
            return false;
        }
        return id != null && id.equals(((PphMotivationGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphMotivationGroup{" +
            "id=" + getId() +
            ", code=" + getCode() +
            ", targetValue=" + getTargetValue() +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            "}";
    }
}
