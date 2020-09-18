package hu.paninform.startmedsol.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import hu.paninform.startmedsol.domain.enumeration.DoctorQualificationValidatorRuleType;
import hu.paninform.startmedsol.domain.enumeration.PrescriptionSubsidyCategory;
import hu.paninform.startmedsol.domain.enumeration.PrescriptionStatus;
import hu.paninform.startmedsol.domain.enumeration.EesztSendingStatus;
import hu.paninform.startmedsol.domain.enumeration.MedicalProductType;
import hu.paninform.startmedsol.domain.enumeration.Frequency;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.Prescription} entity.
 */
public class PrescriptionDTO implements Serializable {
    
    private Long id;

    private Boolean openedPackage;

    private Boolean medicalAidTeachPrescribing;

    private Boolean forH2cCertificate;

    @NotNull
    private Instant inscriptionDate;

    @Size(max = 50)
    private String causeOfNotReplaceability;

    @Size(max = 1024)
    private String description;

    @Size(max = 32)
    private String doseMsmUnit;

    @Size(max = 36)
    private String eesztGroupId;

    @Size(max = 1024)
    private String institution;

    @Size(max = 255)
    private String instructions;

    @Size(max = 50)
    private String integrationId;

    private Integer equippedWith;

    @NotNull
    private DoctorQualificationValidatorRuleType acceptedQualificationRule;

    private PrescriptionSubsidyCategory subsidyCategory;

    @NotNull
    private PrescriptionStatus status;

    private EesztSendingStatus eesztSendingStatus;

    @NotNull
    private MedicalProductType medicalProductType;

    @Size(max = 50)
    private String preparationDescription;

    @Size(max = 255)
    private String invalidationReason;

    @NotNull
    private Frequency frequency;

    private Double frequencyMultiplier;

    private Double quantityMultiplier;

    private Double morning;

    private Double noon;

    private Double evening;

    private Double beforeSleep;

    @Size(max = 100)
    private String dosagePatternText;

    private Integer quantity;

    @Size(max = 50)
    private String quantityCause;

    @NotNull
    private Boolean forSeveralMonths;

    private Integer monthsSuppliedFor;

    private Boolean forOnePrescription;

    @Size(max = 16)
    private String specialistRegNo;

    private Instant proposalDate;


    private Long diagnosisId;

    private Long inscriberDoctorId;

    private Long qualificationRuleAcceptorId;

    private Long medicineId;

    private Long medicalCaseId;

    private Long puphaVersionId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isOpenedPackage() {
        return openedPackage;
    }

    public void setOpenedPackage(Boolean openedPackage) {
        this.openedPackage = openedPackage;
    }

    public Boolean isMedicalAidTeachPrescribing() {
        return medicalAidTeachPrescribing;
    }

    public void setMedicalAidTeachPrescribing(Boolean medicalAidTeachPrescribing) {
        this.medicalAidTeachPrescribing = medicalAidTeachPrescribing;
    }

    public Boolean isForH2cCertificate() {
        return forH2cCertificate;
    }

    public void setForH2cCertificate(Boolean forH2cCertificate) {
        this.forH2cCertificate = forH2cCertificate;
    }

    public Instant getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(Instant inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public String getCauseOfNotReplaceability() {
        return causeOfNotReplaceability;
    }

    public void setCauseOfNotReplaceability(String causeOfNotReplaceability) {
        this.causeOfNotReplaceability = causeOfNotReplaceability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoseMsmUnit() {
        return doseMsmUnit;
    }

    public void setDoseMsmUnit(String doseMsmUnit) {
        this.doseMsmUnit = doseMsmUnit;
    }

    public String getEesztGroupId() {
        return eesztGroupId;
    }

    public void setEesztGroupId(String eesztGroupId) {
        this.eesztGroupId = eesztGroupId;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    public Integer getEquippedWith() {
        return equippedWith;
    }

    public void setEquippedWith(Integer equippedWith) {
        this.equippedWith = equippedWith;
    }

    public DoctorQualificationValidatorRuleType getAcceptedQualificationRule() {
        return acceptedQualificationRule;
    }

    public void setAcceptedQualificationRule(DoctorQualificationValidatorRuleType acceptedQualificationRule) {
        this.acceptedQualificationRule = acceptedQualificationRule;
    }

    public PrescriptionSubsidyCategory getSubsidyCategory() {
        return subsidyCategory;
    }

    public void setSubsidyCategory(PrescriptionSubsidyCategory subsidyCategory) {
        this.subsidyCategory = subsidyCategory;
    }

    public PrescriptionStatus getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }

    public EesztSendingStatus getEesztSendingStatus() {
        return eesztSendingStatus;
    }

    public void setEesztSendingStatus(EesztSendingStatus eesztSendingStatus) {
        this.eesztSendingStatus = eesztSendingStatus;
    }

    public MedicalProductType getMedicalProductType() {
        return medicalProductType;
    }

    public void setMedicalProductType(MedicalProductType medicalProductType) {
        this.medicalProductType = medicalProductType;
    }

    public String getPreparationDescription() {
        return preparationDescription;
    }

    public void setPreparationDescription(String preparationDescription) {
        this.preparationDescription = preparationDescription;
    }

    public String getInvalidationReason() {
        return invalidationReason;
    }

    public void setInvalidationReason(String invalidationReason) {
        this.invalidationReason = invalidationReason;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Double getFrequencyMultiplier() {
        return frequencyMultiplier;
    }

    public void setFrequencyMultiplier(Double frequencyMultiplier) {
        this.frequencyMultiplier = frequencyMultiplier;
    }

    public Double getQuantityMultiplier() {
        return quantityMultiplier;
    }

    public void setQuantityMultiplier(Double quantityMultiplier) {
        this.quantityMultiplier = quantityMultiplier;
    }

    public Double getMorning() {
        return morning;
    }

    public void setMorning(Double morning) {
        this.morning = morning;
    }

    public Double getNoon() {
        return noon;
    }

    public void setNoon(Double noon) {
        this.noon = noon;
    }

    public Double getEvening() {
        return evening;
    }

    public void setEvening(Double evening) {
        this.evening = evening;
    }

    public Double getBeforeSleep() {
        return beforeSleep;
    }

    public void setBeforeSleep(Double beforeSleep) {
        this.beforeSleep = beforeSleep;
    }

    public String getDosagePatternText() {
        return dosagePatternText;
    }

    public void setDosagePatternText(String dosagePatternText) {
        this.dosagePatternText = dosagePatternText;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getQuantityCause() {
        return quantityCause;
    }

    public void setQuantityCause(String quantityCause) {
        this.quantityCause = quantityCause;
    }

    public Boolean isForSeveralMonths() {
        return forSeveralMonths;
    }

    public void setForSeveralMonths(Boolean forSeveralMonths) {
        this.forSeveralMonths = forSeveralMonths;
    }

    public Integer getMonthsSuppliedFor() {
        return monthsSuppliedFor;
    }

    public void setMonthsSuppliedFor(Integer monthsSuppliedFor) {
        this.monthsSuppliedFor = monthsSuppliedFor;
    }

    public Boolean isForOnePrescription() {
        return forOnePrescription;
    }

    public void setForOnePrescription(Boolean forOnePrescription) {
        this.forOnePrescription = forOnePrescription;
    }

    public String getSpecialistRegNo() {
        return specialistRegNo;
    }

    public void setSpecialistRegNo(String specialistRegNo) {
        this.specialistRegNo = specialistRegNo;
    }

    public Instant getProposalDate() {
        return proposalDate;
    }

    public void setProposalDate(Instant proposalDate) {
        this.proposalDate = proposalDate;
    }

    public Long getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Long csDiagnosisId) {
        this.diagnosisId = csDiagnosisId;
    }

    public Long getInscriberDoctorId() {
        return inscriberDoctorId;
    }

    public void setInscriberDoctorId(Long employeeId) {
        this.inscriberDoctorId = employeeId;
    }

    public Long getQualificationRuleAcceptorId() {
        return qualificationRuleAcceptorId;
    }

    public void setQualificationRuleAcceptorId(Long employeeId) {
        this.qualificationRuleAcceptorId = employeeId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long pphMedicineId) {
        this.medicineId = pphMedicineId;
    }

    public Long getMedicalCaseId() {
        return medicalCaseId;
    }

    public void setMedicalCaseId(Long medicalCaseId) {
        this.medicalCaseId = medicalCaseId;
    }

    public Long getPuphaVersionId() {
        return puphaVersionId;
    }

    public void setPuphaVersionId(Long pphPuphaVersionId) {
        this.puphaVersionId = pphPuphaVersionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrescriptionDTO)) {
            return false;
        }

        return id != null && id.equals(((PrescriptionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrescriptionDTO{" +
            "id=" + getId() +
            ", openedPackage='" + isOpenedPackage() + "'" +
            ", medicalAidTeachPrescribing='" + isMedicalAidTeachPrescribing() + "'" +
            ", forH2cCertificate='" + isForH2cCertificate() + "'" +
            ", inscriptionDate='" + getInscriptionDate() + "'" +
            ", causeOfNotReplaceability='" + getCauseOfNotReplaceability() + "'" +
            ", description='" + getDescription() + "'" +
            ", doseMsmUnit='" + getDoseMsmUnit() + "'" +
            ", eesztGroupId='" + getEesztGroupId() + "'" +
            ", institution='" + getInstitution() + "'" +
            ", instructions='" + getInstructions() + "'" +
            ", integrationId='" + getIntegrationId() + "'" +
            ", equippedWith=" + getEquippedWith() +
            ", acceptedQualificationRule='" + getAcceptedQualificationRule() + "'" +
            ", subsidyCategory='" + getSubsidyCategory() + "'" +
            ", status='" + getStatus() + "'" +
            ", eesztSendingStatus='" + getEesztSendingStatus() + "'" +
            ", medicalProductType='" + getMedicalProductType() + "'" +
            ", preparationDescription='" + getPreparationDescription() + "'" +
            ", invalidationReason='" + getInvalidationReason() + "'" +
            ", frequency='" + getFrequency() + "'" +
            ", frequencyMultiplier=" + getFrequencyMultiplier() +
            ", quantityMultiplier=" + getQuantityMultiplier() +
            ", morning=" + getMorning() +
            ", noon=" + getNoon() +
            ", evening=" + getEvening() +
            ", beforeSleep=" + getBeforeSleep() +
            ", dosagePatternText='" + getDosagePatternText() + "'" +
            ", quantity=" + getQuantity() +
            ", quantityCause='" + getQuantityCause() + "'" +
            ", forSeveralMonths='" + isForSeveralMonths() + "'" +
            ", monthsSuppliedFor=" + getMonthsSuppliedFor() +
            ", forOnePrescription='" + isForOnePrescription() + "'" +
            ", specialistRegNo='" + getSpecialistRegNo() + "'" +
            ", proposalDate='" + getProposalDate() + "'" +
            ", diagnosisId=" + getDiagnosisId() +
            ", inscriberDoctorId=" + getInscriberDoctorId() +
            ", qualificationRuleAcceptorId=" + getQualificationRuleAcceptorId() +
            ", medicineId=" + getMedicineId() +
            ", medicalCaseId=" + getMedicalCaseId() +
            ", puphaVersionId=" + getPuphaVersionId() +
            "}";
    }
}
