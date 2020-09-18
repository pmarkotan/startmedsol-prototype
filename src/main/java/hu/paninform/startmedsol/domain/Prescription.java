package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import hu.paninform.startmedsol.domain.enumeration.DoctorQualificationValidatorRuleType;

import hu.paninform.startmedsol.domain.enumeration.PrescriptionSubsidyCategory;

import hu.paninform.startmedsol.domain.enumeration.PrescriptionStatus;

import hu.paninform.startmedsol.domain.enumeration.EesztSendingStatus;

import hu.paninform.startmedsol.domain.enumeration.MedicalProductType;

import hu.paninform.startmedsol.domain.enumeration.Frequency;

/**
 * A Prescription.
 */
@Entity
@Table(name = "prescription")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Prescription implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "opened_package")
    private Boolean openedPackage;

    @Column(name = "medical_aid_teach_prescribing")
    private Boolean medicalAidTeachPrescribing;

    @Column(name = "for_h_2_c_certificate")
    private Boolean forH2cCertificate;

    @NotNull
    @Column(name = "inscription_date", nullable = false)
    private Instant inscriptionDate;

    @Size(max = 50)
    @Column(name = "cause_of_not_replaceability", length = 50)
    private String causeOfNotReplaceability;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
    private String description;

    @Size(max = 32)
    @Column(name = "dose_msm_unit", length = 32)
    private String doseMsmUnit;

    @Size(max = 36)
    @Column(name = "eeszt_group_id", length = 36)
    private String eesztGroupId;

    @Size(max = 1024)
    @Column(name = "institution", length = 1024)
    private String institution;

    @Size(max = 255)
    @Column(name = "instructions", length = 255)
    private String instructions;

    @Size(max = 50)
    @Column(name = "integration_id", length = 50)
    private String integrationId;

    @Column(name = "equipped_with")
    private Integer equippedWith;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "accepted_qualification_rule", nullable = false)
    private DoctorQualificationValidatorRuleType acceptedQualificationRule;

    @Enumerated(EnumType.STRING)
    @Column(name = "subsidy_category")
    private PrescriptionSubsidyCategory subsidyCategory;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PrescriptionStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "eeszt_sending_status")
    private EesztSendingStatus eesztSendingStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "medical_product_type", nullable = false)
    private MedicalProductType medicalProductType;

    @Size(max = 50)
    @Column(name = "preparation_description", length = 50)
    private String preparationDescription;

    @Size(max = 255)
    @Column(name = "invalidation_reason", length = 255)
    private String invalidationReason;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "frequency", nullable = false)
    private Frequency frequency;

    @Column(name = "frequency_multiplier")
    private Double frequencyMultiplier;

    @Column(name = "quantity_multiplier")
    private Double quantityMultiplier;

    @Column(name = "morning")
    private Double morning;

    @Column(name = "noon")
    private Double noon;

    @Column(name = "evening")
    private Double evening;

    @Column(name = "before_sleep")
    private Double beforeSleep;

    @Size(max = 100)
    @Column(name = "dosage_pattern_text", length = 100)
    private String dosagePatternText;

    @Column(name = "quantity")
    private Integer quantity;

    @Size(max = 50)
    @Column(name = "quantity_cause", length = 50)
    private String quantityCause;

    @NotNull
    @Column(name = "for_several_months", nullable = false)
    private Boolean forSeveralMonths;

    @Column(name = "months_supplied_for")
    private Integer monthsSuppliedFor;

    @Column(name = "for_one_prescription")
    private Boolean forOnePrescription;

    @Size(max = 16)
    @Column(name = "specialist_reg_no", length = 16)
    private String specialistRegNo;

    @Column(name = "proposal_date")
    private Instant proposalDate;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "prescriptions", allowSetters = true)
    private CsDiagnosis diagnosis;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "prescriptions", allowSetters = true)
    private Employee inscriberDoctor;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "prescriptions", allowSetters = true)
    private Employee qualificationRuleAcceptor;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "prescriptions", allowSetters = true)
    private PphMedicine medicine;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "prescriptions", allowSetters = true)
    private MedicalCase medicalCase;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "prescriptions", allowSetters = true)
    private PphPuphaVersion puphaVersion;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isOpenedPackage() {
        return openedPackage;
    }

    public Prescription openedPackage(Boolean openedPackage) {
        this.openedPackage = openedPackage;
        return this;
    }

    public void setOpenedPackage(Boolean openedPackage) {
        this.openedPackage = openedPackage;
    }

    public Boolean isMedicalAidTeachPrescribing() {
        return medicalAidTeachPrescribing;
    }

    public Prescription medicalAidTeachPrescribing(Boolean medicalAidTeachPrescribing) {
        this.medicalAidTeachPrescribing = medicalAidTeachPrescribing;
        return this;
    }

    public void setMedicalAidTeachPrescribing(Boolean medicalAidTeachPrescribing) {
        this.medicalAidTeachPrescribing = medicalAidTeachPrescribing;
    }

    public Boolean isForH2cCertificate() {
        return forH2cCertificate;
    }

    public Prescription forH2cCertificate(Boolean forH2cCertificate) {
        this.forH2cCertificate = forH2cCertificate;
        return this;
    }

    public void setForH2cCertificate(Boolean forH2cCertificate) {
        this.forH2cCertificate = forH2cCertificate;
    }

    public Instant getInscriptionDate() {
        return inscriptionDate;
    }

    public Prescription inscriptionDate(Instant inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
        return this;
    }

    public void setInscriptionDate(Instant inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public String getCauseOfNotReplaceability() {
        return causeOfNotReplaceability;
    }

    public Prescription causeOfNotReplaceability(String causeOfNotReplaceability) {
        this.causeOfNotReplaceability = causeOfNotReplaceability;
        return this;
    }

    public void setCauseOfNotReplaceability(String causeOfNotReplaceability) {
        this.causeOfNotReplaceability = causeOfNotReplaceability;
    }

    public String getDescription() {
        return description;
    }

    public Prescription description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoseMsmUnit() {
        return doseMsmUnit;
    }

    public Prescription doseMsmUnit(String doseMsmUnit) {
        this.doseMsmUnit = doseMsmUnit;
        return this;
    }

    public void setDoseMsmUnit(String doseMsmUnit) {
        this.doseMsmUnit = doseMsmUnit;
    }

    public String getEesztGroupId() {
        return eesztGroupId;
    }

    public Prescription eesztGroupId(String eesztGroupId) {
        this.eesztGroupId = eesztGroupId;
        return this;
    }

    public void setEesztGroupId(String eesztGroupId) {
        this.eesztGroupId = eesztGroupId;
    }

    public String getInstitution() {
        return institution;
    }

    public Prescription institution(String institution) {
        this.institution = institution;
        return this;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getInstructions() {
        return instructions;
    }

    public Prescription instructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public Prescription integrationId(String integrationId) {
        this.integrationId = integrationId;
        return this;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    public Integer getEquippedWith() {
        return equippedWith;
    }

    public Prescription equippedWith(Integer equippedWith) {
        this.equippedWith = equippedWith;
        return this;
    }

    public void setEquippedWith(Integer equippedWith) {
        this.equippedWith = equippedWith;
    }

    public DoctorQualificationValidatorRuleType getAcceptedQualificationRule() {
        return acceptedQualificationRule;
    }

    public Prescription acceptedQualificationRule(DoctorQualificationValidatorRuleType acceptedQualificationRule) {
        this.acceptedQualificationRule = acceptedQualificationRule;
        return this;
    }

    public void setAcceptedQualificationRule(DoctorQualificationValidatorRuleType acceptedQualificationRule) {
        this.acceptedQualificationRule = acceptedQualificationRule;
    }

    public PrescriptionSubsidyCategory getSubsidyCategory() {
        return subsidyCategory;
    }

    public Prescription subsidyCategory(PrescriptionSubsidyCategory subsidyCategory) {
        this.subsidyCategory = subsidyCategory;
        return this;
    }

    public void setSubsidyCategory(PrescriptionSubsidyCategory subsidyCategory) {
        this.subsidyCategory = subsidyCategory;
    }

    public PrescriptionStatus getStatus() {
        return status;
    }

    public Prescription status(PrescriptionStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }

    public EesztSendingStatus getEesztSendingStatus() {
        return eesztSendingStatus;
    }

    public Prescription eesztSendingStatus(EesztSendingStatus eesztSendingStatus) {
        this.eesztSendingStatus = eesztSendingStatus;
        return this;
    }

    public void setEesztSendingStatus(EesztSendingStatus eesztSendingStatus) {
        this.eesztSendingStatus = eesztSendingStatus;
    }

    public MedicalProductType getMedicalProductType() {
        return medicalProductType;
    }

    public Prescription medicalProductType(MedicalProductType medicalProductType) {
        this.medicalProductType = medicalProductType;
        return this;
    }

    public void setMedicalProductType(MedicalProductType medicalProductType) {
        this.medicalProductType = medicalProductType;
    }

    public String getPreparationDescription() {
        return preparationDescription;
    }

    public Prescription preparationDescription(String preparationDescription) {
        this.preparationDescription = preparationDescription;
        return this;
    }

    public void setPreparationDescription(String preparationDescription) {
        this.preparationDescription = preparationDescription;
    }

    public String getInvalidationReason() {
        return invalidationReason;
    }

    public Prescription invalidationReason(String invalidationReason) {
        this.invalidationReason = invalidationReason;
        return this;
    }

    public void setInvalidationReason(String invalidationReason) {
        this.invalidationReason = invalidationReason;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public Prescription frequency(Frequency frequency) {
        this.frequency = frequency;
        return this;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Double getFrequencyMultiplier() {
        return frequencyMultiplier;
    }

    public Prescription frequencyMultiplier(Double frequencyMultiplier) {
        this.frequencyMultiplier = frequencyMultiplier;
        return this;
    }

    public void setFrequencyMultiplier(Double frequencyMultiplier) {
        this.frequencyMultiplier = frequencyMultiplier;
    }

    public Double getQuantityMultiplier() {
        return quantityMultiplier;
    }

    public Prescription quantityMultiplier(Double quantityMultiplier) {
        this.quantityMultiplier = quantityMultiplier;
        return this;
    }

    public void setQuantityMultiplier(Double quantityMultiplier) {
        this.quantityMultiplier = quantityMultiplier;
    }

    public Double getMorning() {
        return morning;
    }

    public Prescription morning(Double morning) {
        this.morning = morning;
        return this;
    }

    public void setMorning(Double morning) {
        this.morning = morning;
    }

    public Double getNoon() {
        return noon;
    }

    public Prescription noon(Double noon) {
        this.noon = noon;
        return this;
    }

    public void setNoon(Double noon) {
        this.noon = noon;
    }

    public Double getEvening() {
        return evening;
    }

    public Prescription evening(Double evening) {
        this.evening = evening;
        return this;
    }

    public void setEvening(Double evening) {
        this.evening = evening;
    }

    public Double getBeforeSleep() {
        return beforeSleep;
    }

    public Prescription beforeSleep(Double beforeSleep) {
        this.beforeSleep = beforeSleep;
        return this;
    }

    public void setBeforeSleep(Double beforeSleep) {
        this.beforeSleep = beforeSleep;
    }

    public String getDosagePatternText() {
        return dosagePatternText;
    }

    public Prescription dosagePatternText(String dosagePatternText) {
        this.dosagePatternText = dosagePatternText;
        return this;
    }

    public void setDosagePatternText(String dosagePatternText) {
        this.dosagePatternText = dosagePatternText;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Prescription quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getQuantityCause() {
        return quantityCause;
    }

    public Prescription quantityCause(String quantityCause) {
        this.quantityCause = quantityCause;
        return this;
    }

    public void setQuantityCause(String quantityCause) {
        this.quantityCause = quantityCause;
    }

    public Boolean isForSeveralMonths() {
        return forSeveralMonths;
    }

    public Prescription forSeveralMonths(Boolean forSeveralMonths) {
        this.forSeveralMonths = forSeveralMonths;
        return this;
    }

    public void setForSeveralMonths(Boolean forSeveralMonths) {
        this.forSeveralMonths = forSeveralMonths;
    }

    public Integer getMonthsSuppliedFor() {
        return monthsSuppliedFor;
    }

    public Prescription monthsSuppliedFor(Integer monthsSuppliedFor) {
        this.monthsSuppliedFor = monthsSuppliedFor;
        return this;
    }

    public void setMonthsSuppliedFor(Integer monthsSuppliedFor) {
        this.monthsSuppliedFor = monthsSuppliedFor;
    }

    public Boolean isForOnePrescription() {
        return forOnePrescription;
    }

    public Prescription forOnePrescription(Boolean forOnePrescription) {
        this.forOnePrescription = forOnePrescription;
        return this;
    }

    public void setForOnePrescription(Boolean forOnePrescription) {
        this.forOnePrescription = forOnePrescription;
    }

    public String getSpecialistRegNo() {
        return specialistRegNo;
    }

    public Prescription specialistRegNo(String specialistRegNo) {
        this.specialistRegNo = specialistRegNo;
        return this;
    }

    public void setSpecialistRegNo(String specialistRegNo) {
        this.specialistRegNo = specialistRegNo;
    }

    public Instant getProposalDate() {
        return proposalDate;
    }

    public Prescription proposalDate(Instant proposalDate) {
        this.proposalDate = proposalDate;
        return this;
    }

    public void setProposalDate(Instant proposalDate) {
        this.proposalDate = proposalDate;
    }

    public CsDiagnosis getDiagnosis() {
        return diagnosis;
    }

    public Prescription diagnosis(CsDiagnosis csDiagnosis) {
        this.diagnosis = csDiagnosis;
        return this;
    }

    public void setDiagnosis(CsDiagnosis csDiagnosis) {
        this.diagnosis = csDiagnosis;
    }

    public Employee getInscriberDoctor() {
        return inscriberDoctor;
    }

    public Prescription inscriberDoctor(Employee employee) {
        this.inscriberDoctor = employee;
        return this;
    }

    public void setInscriberDoctor(Employee employee) {
        this.inscriberDoctor = employee;
    }

    public Employee getQualificationRuleAcceptor() {
        return qualificationRuleAcceptor;
    }

    public Prescription qualificationRuleAcceptor(Employee employee) {
        this.qualificationRuleAcceptor = employee;
        return this;
    }

    public void setQualificationRuleAcceptor(Employee employee) {
        this.qualificationRuleAcceptor = employee;
    }

    public PphMedicine getMedicine() {
        return medicine;
    }

    public Prescription medicine(PphMedicine pphMedicine) {
        this.medicine = pphMedicine;
        return this;
    }

    public void setMedicine(PphMedicine pphMedicine) {
        this.medicine = pphMedicine;
    }

    public MedicalCase getMedicalCase() {
        return medicalCase;
    }

    public Prescription medicalCase(MedicalCase medicalCase) {
        this.medicalCase = medicalCase;
        return this;
    }

    public void setMedicalCase(MedicalCase medicalCase) {
        this.medicalCase = medicalCase;
    }

    public PphPuphaVersion getPuphaVersion() {
        return puphaVersion;
    }

    public Prescription puphaVersion(PphPuphaVersion pphPuphaVersion) {
        this.puphaVersion = pphPuphaVersion;
        return this;
    }

    public void setPuphaVersion(PphPuphaVersion pphPuphaVersion) {
        this.puphaVersion = pphPuphaVersion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Prescription)) {
            return false;
        }
        return id != null && id.equals(((Prescription) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Prescription{" +
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
            "}";
    }
}
