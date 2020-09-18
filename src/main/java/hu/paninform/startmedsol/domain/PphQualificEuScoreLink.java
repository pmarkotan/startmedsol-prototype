package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import hu.paninform.startmedsol.domain.enumeration.InstitutionCategory;

import hu.paninform.startmedsol.domain.enumeration.PrescriptionRight;

/**
 * A PphQualificEuScoreLink.
 */
@Entity
@Table(name = "pph_qualific_eu_score_link")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PphQualificEuScoreLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "time_limit")
    private Integer timeLimit;

    @NotNull
    @Column(name = "active_pupha_data", nullable = false)
    private Boolean activePuphaData;

    @Enumerated(EnumType.STRING)
    @Column(name = "institution_category")
    private InstitutionCategory institutionCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "prescription_right")
    private PrescriptionRight prescriptionRight;

    @ManyToOne
    @JsonIgnoreProperties(value = "pphQualificEuScoreLinks", allowSetters = true)
    private PphQualification qualification;

    @ManyToOne
    @JsonIgnoreProperties(value = "pphQualificEuScoreLinks", allowSetters = true)
    private PphPuphaInstitution puphaInstitution;

    @ManyToOne
    @JsonIgnoreProperties(value = "qualificEuScoreLinks", allowSetters = true)
    private PphEuScore euScore;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public PphQualificEuScoreLink timeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
        return this;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public PphQualificEuScoreLink activePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
        return this;
    }

    public void setActivePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
    }

    public InstitutionCategory getInstitutionCategory() {
        return institutionCategory;
    }

    public PphQualificEuScoreLink institutionCategory(InstitutionCategory institutionCategory) {
        this.institutionCategory = institutionCategory;
        return this;
    }

    public void setInstitutionCategory(InstitutionCategory institutionCategory) {
        this.institutionCategory = institutionCategory;
    }

    public PrescriptionRight getPrescriptionRight() {
        return prescriptionRight;
    }

    public PphQualificEuScoreLink prescriptionRight(PrescriptionRight prescriptionRight) {
        this.prescriptionRight = prescriptionRight;
        return this;
    }

    public void setPrescriptionRight(PrescriptionRight prescriptionRight) {
        this.prescriptionRight = prescriptionRight;
    }

    public PphQualification getQualification() {
        return qualification;
    }

    public PphQualificEuScoreLink qualification(PphQualification pphQualification) {
        this.qualification = pphQualification;
        return this;
    }

    public void setQualification(PphQualification pphQualification) {
        this.qualification = pphQualification;
    }

    public PphPuphaInstitution getPuphaInstitution() {
        return puphaInstitution;
    }

    public PphQualificEuScoreLink puphaInstitution(PphPuphaInstitution pphPuphaInstitution) {
        this.puphaInstitution = pphPuphaInstitution;
        return this;
    }

    public void setPuphaInstitution(PphPuphaInstitution pphPuphaInstitution) {
        this.puphaInstitution = pphPuphaInstitution;
    }

    public PphEuScore getEuScore() {
        return euScore;
    }

    public PphQualificEuScoreLink euScore(PphEuScore pphEuScore) {
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
        if (!(o instanceof PphQualificEuScoreLink)) {
            return false;
        }
        return id != null && id.equals(((PphQualificEuScoreLink) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphQualificEuScoreLink{" +
            "id=" + getId() +
            ", timeLimit=" + getTimeLimit() +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            ", institutionCategory='" + getInstitutionCategory() + "'" +
            ", prescriptionRight='" + getPrescriptionRight() + "'" +
            "}";
    }
}
