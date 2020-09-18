package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A SpecialistsAdvice.
 */
@Entity
@Table(name = "specialists_advice")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpecialistsAdvice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 256)
    @Column(name = "period_of_time", length = 256)
    private String periodOfTime;

    @Size(max = 64)
    @Column(name = "raised_indication_code", length = 64)
    private String raisedIndicationCode;

    @Size(max = 64)
    @Column(name = "raised_subsidy_percent", length = 64)
    private String raisedSubsidyPercent;

    @Size(max = 64)
    @Column(name = "emphasized_indication_code", length = 64)
    private String emphasizedIndicationCode;

    @Size(max = 256)
    @Column(name = "active_substance", length = 256)
    private String activeSubstance;

    @Size(max = 256)
    @Column(name = "efficacy", length = 256)
    private String efficacy;

    @Size(max = 256)
    @Column(name = "dosage_mod", length = 256)
    private String dosageMod;

    @Size(max = 256)
    @Column(name = "dosage", length = 256)
    private String dosage;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "specialistsAdvices", allowSetters = true)
    private MedicalCase medicalCase;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "specialistsAdvices", allowSetters = true)
    private CsDiagnosis diagnosis;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriodOfTime() {
        return periodOfTime;
    }

    public SpecialistsAdvice periodOfTime(String periodOfTime) {
        this.periodOfTime = periodOfTime;
        return this;
    }

    public void setPeriodOfTime(String periodOfTime) {
        this.periodOfTime = periodOfTime;
    }

    public String getRaisedIndicationCode() {
        return raisedIndicationCode;
    }

    public SpecialistsAdvice raisedIndicationCode(String raisedIndicationCode) {
        this.raisedIndicationCode = raisedIndicationCode;
        return this;
    }

    public void setRaisedIndicationCode(String raisedIndicationCode) {
        this.raisedIndicationCode = raisedIndicationCode;
    }

    public String getRaisedSubsidyPercent() {
        return raisedSubsidyPercent;
    }

    public SpecialistsAdvice raisedSubsidyPercent(String raisedSubsidyPercent) {
        this.raisedSubsidyPercent = raisedSubsidyPercent;
        return this;
    }

    public void setRaisedSubsidyPercent(String raisedSubsidyPercent) {
        this.raisedSubsidyPercent = raisedSubsidyPercent;
    }

    public String getEmphasizedIndicationCode() {
        return emphasizedIndicationCode;
    }

    public SpecialistsAdvice emphasizedIndicationCode(String emphasizedIndicationCode) {
        this.emphasizedIndicationCode = emphasizedIndicationCode;
        return this;
    }

    public void setEmphasizedIndicationCode(String emphasizedIndicationCode) {
        this.emphasizedIndicationCode = emphasizedIndicationCode;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public SpecialistsAdvice activeSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
        return this;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public SpecialistsAdvice efficacy(String efficacy) {
        this.efficacy = efficacy;
        return this;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getDosageMod() {
        return dosageMod;
    }

    public SpecialistsAdvice dosageMod(String dosageMod) {
        this.dosageMod = dosageMod;
        return this;
    }

    public void setDosageMod(String dosageMod) {
        this.dosageMod = dosageMod;
    }

    public String getDosage() {
        return dosage;
    }

    public SpecialistsAdvice dosage(String dosage) {
        this.dosage = dosage;
        return this;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public MedicalCase getMedicalCase() {
        return medicalCase;
    }

    public SpecialistsAdvice medicalCase(MedicalCase medicalCase) {
        this.medicalCase = medicalCase;
        return this;
    }

    public void setMedicalCase(MedicalCase medicalCase) {
        this.medicalCase = medicalCase;
    }

    public CsDiagnosis getDiagnosis() {
        return diagnosis;
    }

    public SpecialistsAdvice diagnosis(CsDiagnosis csDiagnosis) {
        this.diagnosis = csDiagnosis;
        return this;
    }

    public void setDiagnosis(CsDiagnosis csDiagnosis) {
        this.diagnosis = csDiagnosis;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SpecialistsAdvice)) {
            return false;
        }
        return id != null && id.equals(((SpecialistsAdvice) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SpecialistsAdvice{" +
            "id=" + getId() +
            ", periodOfTime='" + getPeriodOfTime() + "'" +
            ", raisedIndicationCode='" + getRaisedIndicationCode() + "'" +
            ", raisedSubsidyPercent='" + getRaisedSubsidyPercent() + "'" +
            ", emphasizedIndicationCode='" + getEmphasizedIndicationCode() + "'" +
            ", activeSubstance='" + getActiveSubstance() + "'" +
            ", efficacy='" + getEfficacy() + "'" +
            ", dosageMod='" + getDosageMod() + "'" +
            ", dosage='" + getDosage() + "'" +
            "}";
    }
}
