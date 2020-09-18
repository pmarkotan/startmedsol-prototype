package hu.paninform.startmedsol.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PphAtcIndex.
 */
@Entity
@Table(name = "pph_atc_index")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PphAtcIndex implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 8)
    @Column(name = "atc_code", length = 8)
    private String atcCode;

    @Size(max = 250)
    @Column(name = "name_hu", length = 250)
    private String nameHu;

    @Size(max = 250)
    @Column(name = "name_en", length = 250)
    private String nameEn;

    @Size(max = 250)
    @Column(name = "active_substance", length = 250)
    private String activeSubstance;

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

    public String getAtcCode() {
        return atcCode;
    }

    public PphAtcIndex atcCode(String atcCode) {
        this.atcCode = atcCode;
        return this;
    }

    public void setAtcCode(String atcCode) {
        this.atcCode = atcCode;
    }

    public String getNameHu() {
        return nameHu;
    }

    public PphAtcIndex nameHu(String nameHu) {
        this.nameHu = nameHu;
        return this;
    }

    public void setNameHu(String nameHu) {
        this.nameHu = nameHu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public PphAtcIndex nameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public PphAtcIndex activeSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
        return this;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public PphAtcIndex activePuphaData(Boolean activePuphaData) {
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
        if (!(o instanceof PphAtcIndex)) {
            return false;
        }
        return id != null && id.equals(((PphAtcIndex) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphAtcIndex{" +
            "id=" + getId() +
            ", atcCode='" + getAtcCode() + "'" +
            ", nameHu='" + getNameHu() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", activeSubstance='" + getActiveSubstance() + "'" +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            "}";
    }
}
