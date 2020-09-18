package hu.paninform.startmedsol.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.SpecialistsAdvice} entity.
 */
public class SpecialistsAdviceDTO implements Serializable {
    
    private Long id;

    @Size(max = 256)
    private String periodOfTime;

    @Size(max = 64)
    private String raisedIndicationCode;

    @Size(max = 64)
    private String raisedSubsidyPercent;

    @Size(max = 64)
    private String emphasizedIndicationCode;

    @Size(max = 256)
    private String activeSubstance;

    @Size(max = 256)
    private String efficacy;

    @Size(max = 256)
    private String dosageMod;

    @Size(max = 256)
    private String dosage;


    private Long medicalCaseId;

    private Long diagnosisId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriodOfTime() {
        return periodOfTime;
    }

    public void setPeriodOfTime(String periodOfTime) {
        this.periodOfTime = periodOfTime;
    }

    public String getRaisedIndicationCode() {
        return raisedIndicationCode;
    }

    public void setRaisedIndicationCode(String raisedIndicationCode) {
        this.raisedIndicationCode = raisedIndicationCode;
    }

    public String getRaisedSubsidyPercent() {
        return raisedSubsidyPercent;
    }

    public void setRaisedSubsidyPercent(String raisedSubsidyPercent) {
        this.raisedSubsidyPercent = raisedSubsidyPercent;
    }

    public String getEmphasizedIndicationCode() {
        return emphasizedIndicationCode;
    }

    public void setEmphasizedIndicationCode(String emphasizedIndicationCode) {
        this.emphasizedIndicationCode = emphasizedIndicationCode;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getDosageMod() {
        return dosageMod;
    }

    public void setDosageMod(String dosageMod) {
        this.dosageMod = dosageMod;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Long getMedicalCaseId() {
        return medicalCaseId;
    }

    public void setMedicalCaseId(Long medicalCaseId) {
        this.medicalCaseId = medicalCaseId;
    }

    public Long getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Long csDiagnosisId) {
        this.diagnosisId = csDiagnosisId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SpecialistsAdviceDTO)) {
            return false;
        }

        return id != null && id.equals(((SpecialistsAdviceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SpecialistsAdviceDTO{" +
            "id=" + getId() +
            ", periodOfTime='" + getPeriodOfTime() + "'" +
            ", raisedIndicationCode='" + getRaisedIndicationCode() + "'" +
            ", raisedSubsidyPercent='" + getRaisedSubsidyPercent() + "'" +
            ", emphasizedIndicationCode='" + getEmphasizedIndicationCode() + "'" +
            ", activeSubstance='" + getActiveSubstance() + "'" +
            ", efficacy='" + getEfficacy() + "'" +
            ", dosageMod='" + getDosageMod() + "'" +
            ", dosage='" + getDosage() + "'" +
            ", medicalCaseId=" + getMedicalCaseId() +
            ", diagnosisId=" + getDiagnosisId() +
            "}";
    }
}
