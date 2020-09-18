package hu.paninform.startmedsol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import hu.paninform.startmedsol.domain.enumeration.PrescriptionType;

import hu.paninform.startmedsol.domain.enumeration.MedicineType;

import hu.paninform.startmedsol.domain.enumeration.MedicineStatus;

/**
 * A PphMedicine.
 */
@Entity
@Table(name = "pph_medicine")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PphMedicine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Size(max = 64)
    @Column(name = "master_book_id", length = 64)
    private String masterBookId;

    @Size(max = 256)
    @Column(name = "name", length = 256)
    private String name;

    @Size(max = 256)
    @Column(name = "package_description", length = 256)
    private String packageDescription;

    @Size(max = 16)
    @Column(name = "ean_code", length = 16)
    private String eanCode;

    @Size(max = 8)
    @Column(name = "atc_code", length = 8)
    private String atcCode;

    @Size(max = 128)
    @Column(name = "brand", length = 128)
    private String brand;

    @Size(max = 128)
    @Column(name = "active_substance", length = 128)
    private String activeSubstance;

    @Size(max = 64)
    @Column(name = "potency", length = 64)
    private String potency;

    @Column(name = "substance_amount_total")
    private Double substanceAmountTotal;

    @Column(name = "dose_in_package")
    private Integer doseInPackage;

    @Column(name = "substance_amount")
    private Double substanceAmount;

    @Size(max = 32)
    @Column(name = "dose_msm_unit", length = 32)
    private String doseMsmUnit;

    @Size(max = 50)
    @Column(name = "substance_msm_unit", length = 50)
    private String substanceMsmUnit;

    @Column(name = "package_size")
    private Integer packageSize;

    @Size(max = 50)
    @Column(name = "package_msm_unit", length = 50)
    private String packageMsmUnit;

    @Column(name = "daily_doze")
    private Double dailyDoze;

    @Size(max = 50)
    @Column(name = "daily_doze_msm_unit", length = 50)
    private String dailyDozeMsmUnit;

    @Column(name = "dd_msm_unit_factor")
    private Double ddMsmUnitFactor;

    @Column(name = "days_of_therapy")
    private Integer daysOfTherapy;

    @Column(name = "consumer_price")
    private Double consumerPrice;

    @Column(name = "gross_consumer_price")
    private Double grossConsumerPrice;

    @Size(max = 32)
    @Column(name = "efficacy", length = 32)
    private String efficacy;

    @Size(max = 256)
    @Column(name = "old_name", length = 256)
    private String oldName;

    @Size(max = 9)
    @Column(name = "oep_ttt", length = 9)
    private String oepTtt;

    @Column(name = "master_book_deleted")
    private Boolean masterBookDeleted;

    @Column(name = "mbook_deleted_date")
    private Instant mbookDeletedDate;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "motivation_status")
    private Integer motivationStatus;

    @Column(name = "cost_effectivity_code")
    private Integer costEffectivityCode;

    @Column(name = "daily_therapy_cost")
    private Double dailyTherapyCost;

    @Column(name = "equivalence_group_id")
    private Integer equivalenceGroupId;

    @Size(max = 4)
    @Column(name = "old_subsidy_type", length = 4)
    private String oldSubsidyType;

    @Column(name = "preferred_price_flag")
    private Integer preferredPriceFlag;

    @Size(max = 2)
    @Column(name = "pharmacy_flag", length = 2)
    private String pharmacyFlag;

    @Column(name = "custom_made")
    private Boolean customMade;

    @Size(max = 16)
    @Column(name = "medical_device_iso", length = 16)
    private String medicalDeviceIso;

    @Column(name = "producer_price")
    private Double producerPrice;

    @Column(name = "wholesale_price")
    private Double wholesalePrice;

    @Column(name = "vat_rate")
    private Double vatRate;

    @Column(name = "army_prescription")
    private Boolean armyPrescription;

    @Column(name = "public_supply")
    private Boolean publicSupply;

    @Column(name = "work_accident_prescr")
    private Boolean workAccidentPrescr;

    @Column(name = "extra_subsidy_prescr")
    private Boolean extraSubsidyPrescr;

    @Column(name = "medical_subsidy_prescr")
    private Boolean medicalSubsidyPrescr;

    @Column(name = "substance_price")
    private Double substancePrice;

    @Column(name = "include_in_hc_2")
    private Integer includeInHc2;

    @Enumerated(EnumType.STRING)
    @Column(name = "prescription_type")
    private PrescriptionType prescriptionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "medicine_type")
    private MedicineType medicineType;

    @Enumerated(EnumType.STRING)
    @Column(name = "medicine_status")
    private MedicineStatus medicineStatus;

    @Column(name = "normative")
    private Boolean normative;

    @Column(name = "ogyi_id")
    private Long ogyiId;

    @Column(name = "norm_fix_group_id")
    private Integer normFixGroupId;

    @Column(name = "extra_subsidy_fix_group_id")
    private Integer extraSubsidyFixGroupId;

    @Column(name = "medical_subsidy_prescription_fix_group_id")
    private Integer medicalSubsidyPrescriptionFixGroupId;

    @Size(max = 256)
    @Column(name = "dosage_mod", length = 256)
    private String dosageMod;

    @NotNull
    @Column(name = "active_pupha_data", nullable = false)
    private Boolean activePuphaData;

    @OneToMany(mappedBy = "medicine")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PphSubsidy> subSidies = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "pphMedicines", allowSetters = true)
    private PphCompany dealerId;

    @ManyToOne
    @JsonIgnoreProperties(value = "pphMedicines", allowSetters = true)
    private PphCompany marketingAuthOwner;

    @ManyToOne
    @JsonIgnoreProperties(value = "pphMedicines", allowSetters = true)
    private PphMedicineForm medicineForm;

    @ManyToOne
    @JsonIgnoreProperties(value = "pphMedicines", allowSetters = true)
    private PphMotivationGroup motivationGroup;

    @ManyToOne
    @JsonIgnoreProperties(value = "pphMedicines", allowSetters = true)
    private PphNiche niche;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "pph_medicine_eu_scores",
               joinColumns = @JoinColumn(name = "pph_medicine_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "eu_scores_id", referencedColumnName = "id"))
    private Set<PphEuScore> euScores = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "medicines", allowSetters = true)
    private PphMedicineQualifiedName qualifiedName;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public PphMedicine productId(Long productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getMasterBookId() {
        return masterBookId;
    }

    public PphMedicine masterBookId(String masterBookId) {
        this.masterBookId = masterBookId;
        return this;
    }

    public void setMasterBookId(String masterBookId) {
        this.masterBookId = masterBookId;
    }

    public String getName() {
        return name;
    }

    public PphMedicine name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public PphMedicine packageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
        return this;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public String getEanCode() {
        return eanCode;
    }

    public PphMedicine eanCode(String eanCode) {
        this.eanCode = eanCode;
        return this;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    public String getAtcCode() {
        return atcCode;
    }

    public PphMedicine atcCode(String atcCode) {
        this.atcCode = atcCode;
        return this;
    }

    public void setAtcCode(String atcCode) {
        this.atcCode = atcCode;
    }

    public String getBrand() {
        return brand;
    }

    public PphMedicine brand(String brand) {
        this.brand = brand;
        return this;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public PphMedicine activeSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
        return this;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public String getPotency() {
        return potency;
    }

    public PphMedicine potency(String potency) {
        this.potency = potency;
        return this;
    }

    public void setPotency(String potency) {
        this.potency = potency;
    }

    public Double getSubstanceAmountTotal() {
        return substanceAmountTotal;
    }

    public PphMedicine substanceAmountTotal(Double substanceAmountTotal) {
        this.substanceAmountTotal = substanceAmountTotal;
        return this;
    }

    public void setSubstanceAmountTotal(Double substanceAmountTotal) {
        this.substanceAmountTotal = substanceAmountTotal;
    }

    public Integer getDoseInPackage() {
        return doseInPackage;
    }

    public PphMedicine doseInPackage(Integer doseInPackage) {
        this.doseInPackage = doseInPackage;
        return this;
    }

    public void setDoseInPackage(Integer doseInPackage) {
        this.doseInPackage = doseInPackage;
    }

    public Double getSubstanceAmount() {
        return substanceAmount;
    }

    public PphMedicine substanceAmount(Double substanceAmount) {
        this.substanceAmount = substanceAmount;
        return this;
    }

    public void setSubstanceAmount(Double substanceAmount) {
        this.substanceAmount = substanceAmount;
    }

    public String getDoseMsmUnit() {
        return doseMsmUnit;
    }

    public PphMedicine doseMsmUnit(String doseMsmUnit) {
        this.doseMsmUnit = doseMsmUnit;
        return this;
    }

    public void setDoseMsmUnit(String doseMsmUnit) {
        this.doseMsmUnit = doseMsmUnit;
    }

    public String getSubstanceMsmUnit() {
        return substanceMsmUnit;
    }

    public PphMedicine substanceMsmUnit(String substanceMsmUnit) {
        this.substanceMsmUnit = substanceMsmUnit;
        return this;
    }

    public void setSubstanceMsmUnit(String substanceMsmUnit) {
        this.substanceMsmUnit = substanceMsmUnit;
    }

    public Integer getPackageSize() {
        return packageSize;
    }

    public PphMedicine packageSize(Integer packageSize) {
        this.packageSize = packageSize;
        return this;
    }

    public void setPackageSize(Integer packageSize) {
        this.packageSize = packageSize;
    }

    public String getPackageMsmUnit() {
        return packageMsmUnit;
    }

    public PphMedicine packageMsmUnit(String packageMsmUnit) {
        this.packageMsmUnit = packageMsmUnit;
        return this;
    }

    public void setPackageMsmUnit(String packageMsmUnit) {
        this.packageMsmUnit = packageMsmUnit;
    }

    public Double getDailyDoze() {
        return dailyDoze;
    }

    public PphMedicine dailyDoze(Double dailyDoze) {
        this.dailyDoze = dailyDoze;
        return this;
    }

    public void setDailyDoze(Double dailyDoze) {
        this.dailyDoze = dailyDoze;
    }

    public String getDailyDozeMsmUnit() {
        return dailyDozeMsmUnit;
    }

    public PphMedicine dailyDozeMsmUnit(String dailyDozeMsmUnit) {
        this.dailyDozeMsmUnit = dailyDozeMsmUnit;
        return this;
    }

    public void setDailyDozeMsmUnit(String dailyDozeMsmUnit) {
        this.dailyDozeMsmUnit = dailyDozeMsmUnit;
    }

    public Double getDdMsmUnitFactor() {
        return ddMsmUnitFactor;
    }

    public PphMedicine ddMsmUnitFactor(Double ddMsmUnitFactor) {
        this.ddMsmUnitFactor = ddMsmUnitFactor;
        return this;
    }

    public void setDdMsmUnitFactor(Double ddMsmUnitFactor) {
        this.ddMsmUnitFactor = ddMsmUnitFactor;
    }

    public Integer getDaysOfTherapy() {
        return daysOfTherapy;
    }

    public PphMedicine daysOfTherapy(Integer daysOfTherapy) {
        this.daysOfTherapy = daysOfTherapy;
        return this;
    }

    public void setDaysOfTherapy(Integer daysOfTherapy) {
        this.daysOfTherapy = daysOfTherapy;
    }

    public Double getConsumerPrice() {
        return consumerPrice;
    }

    public PphMedicine consumerPrice(Double consumerPrice) {
        this.consumerPrice = consumerPrice;
        return this;
    }

    public void setConsumerPrice(Double consumerPrice) {
        this.consumerPrice = consumerPrice;
    }

    public Double getGrossConsumerPrice() {
        return grossConsumerPrice;
    }

    public PphMedicine grossConsumerPrice(Double grossConsumerPrice) {
        this.grossConsumerPrice = grossConsumerPrice;
        return this;
    }

    public void setGrossConsumerPrice(Double grossConsumerPrice) {
        this.grossConsumerPrice = grossConsumerPrice;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public PphMedicine efficacy(String efficacy) {
        this.efficacy = efficacy;
        return this;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getOldName() {
        return oldName;
    }

    public PphMedicine oldName(String oldName) {
        this.oldName = oldName;
        return this;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getOepTtt() {
        return oepTtt;
    }

    public PphMedicine oepTtt(String oepTtt) {
        this.oepTtt = oepTtt;
        return this;
    }

    public void setOepTtt(String oepTtt) {
        this.oepTtt = oepTtt;
    }

    public Boolean isMasterBookDeleted() {
        return masterBookDeleted;
    }

    public PphMedicine masterBookDeleted(Boolean masterBookDeleted) {
        this.masterBookDeleted = masterBookDeleted;
        return this;
    }

    public void setMasterBookDeleted(Boolean masterBookDeleted) {
        this.masterBookDeleted = masterBookDeleted;
    }

    public Instant getMbookDeletedDate() {
        return mbookDeletedDate;
    }

    public PphMedicine mbookDeletedDate(Instant mbookDeletedDate) {
        this.mbookDeletedDate = mbookDeletedDate;
        return this;
    }

    public void setMbookDeletedDate(Instant mbookDeletedDate) {
        this.mbookDeletedDate = mbookDeletedDate;
    }

    public Boolean isAvailable() {
        return available;
    }

    public PphMedicine available(Boolean available) {
        this.available = available;
        return this;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getMotivationStatus() {
        return motivationStatus;
    }

    public PphMedicine motivationStatus(Integer motivationStatus) {
        this.motivationStatus = motivationStatus;
        return this;
    }

    public void setMotivationStatus(Integer motivationStatus) {
        this.motivationStatus = motivationStatus;
    }

    public Integer getCostEffectivityCode() {
        return costEffectivityCode;
    }

    public PphMedicine costEffectivityCode(Integer costEffectivityCode) {
        this.costEffectivityCode = costEffectivityCode;
        return this;
    }

    public void setCostEffectivityCode(Integer costEffectivityCode) {
        this.costEffectivityCode = costEffectivityCode;
    }

    public Double getDailyTherapyCost() {
        return dailyTherapyCost;
    }

    public PphMedicine dailyTherapyCost(Double dailyTherapyCost) {
        this.dailyTherapyCost = dailyTherapyCost;
        return this;
    }

    public void setDailyTherapyCost(Double dailyTherapyCost) {
        this.dailyTherapyCost = dailyTherapyCost;
    }

    public Integer getEquivalenceGroupId() {
        return equivalenceGroupId;
    }

    public PphMedicine equivalenceGroupId(Integer equivalenceGroupId) {
        this.equivalenceGroupId = equivalenceGroupId;
        return this;
    }

    public void setEquivalenceGroupId(Integer equivalenceGroupId) {
        this.equivalenceGroupId = equivalenceGroupId;
    }

    public String getOldSubsidyType() {
        return oldSubsidyType;
    }

    public PphMedicine oldSubsidyType(String oldSubsidyType) {
        this.oldSubsidyType = oldSubsidyType;
        return this;
    }

    public void setOldSubsidyType(String oldSubsidyType) {
        this.oldSubsidyType = oldSubsidyType;
    }

    public Integer getPreferredPriceFlag() {
        return preferredPriceFlag;
    }

    public PphMedicine preferredPriceFlag(Integer preferredPriceFlag) {
        this.preferredPriceFlag = preferredPriceFlag;
        return this;
    }

    public void setPreferredPriceFlag(Integer preferredPriceFlag) {
        this.preferredPriceFlag = preferredPriceFlag;
    }

    public String getPharmacyFlag() {
        return pharmacyFlag;
    }

    public PphMedicine pharmacyFlag(String pharmacyFlag) {
        this.pharmacyFlag = pharmacyFlag;
        return this;
    }

    public void setPharmacyFlag(String pharmacyFlag) {
        this.pharmacyFlag = pharmacyFlag;
    }

    public Boolean isCustomMade() {
        return customMade;
    }

    public PphMedicine customMade(Boolean customMade) {
        this.customMade = customMade;
        return this;
    }

    public void setCustomMade(Boolean customMade) {
        this.customMade = customMade;
    }

    public String getMedicalDeviceIso() {
        return medicalDeviceIso;
    }

    public PphMedicine medicalDeviceIso(String medicalDeviceIso) {
        this.medicalDeviceIso = medicalDeviceIso;
        return this;
    }

    public void setMedicalDeviceIso(String medicalDeviceIso) {
        this.medicalDeviceIso = medicalDeviceIso;
    }

    public Double getProducerPrice() {
        return producerPrice;
    }

    public PphMedicine producerPrice(Double producerPrice) {
        this.producerPrice = producerPrice;
        return this;
    }

    public void setProducerPrice(Double producerPrice) {
        this.producerPrice = producerPrice;
    }

    public Double getWholesalePrice() {
        return wholesalePrice;
    }

    public PphMedicine wholesalePrice(Double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
        return this;
    }

    public void setWholesalePrice(Double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public Double getVatRate() {
        return vatRate;
    }

    public PphMedicine vatRate(Double vatRate) {
        this.vatRate = vatRate;
        return this;
    }

    public void setVatRate(Double vatRate) {
        this.vatRate = vatRate;
    }

    public Boolean isArmyPrescription() {
        return armyPrescription;
    }

    public PphMedicine armyPrescription(Boolean armyPrescription) {
        this.armyPrescription = armyPrescription;
        return this;
    }

    public void setArmyPrescription(Boolean armyPrescription) {
        this.armyPrescription = armyPrescription;
    }

    public Boolean isPublicSupply() {
        return publicSupply;
    }

    public PphMedicine publicSupply(Boolean publicSupply) {
        this.publicSupply = publicSupply;
        return this;
    }

    public void setPublicSupply(Boolean publicSupply) {
        this.publicSupply = publicSupply;
    }

    public Boolean isWorkAccidentPrescr() {
        return workAccidentPrescr;
    }

    public PphMedicine workAccidentPrescr(Boolean workAccidentPrescr) {
        this.workAccidentPrescr = workAccidentPrescr;
        return this;
    }

    public void setWorkAccidentPrescr(Boolean workAccidentPrescr) {
        this.workAccidentPrescr = workAccidentPrescr;
    }

    public Boolean isExtraSubsidyPrescr() {
        return extraSubsidyPrescr;
    }

    public PphMedicine extraSubsidyPrescr(Boolean extraSubsidyPrescr) {
        this.extraSubsidyPrescr = extraSubsidyPrescr;
        return this;
    }

    public void setExtraSubsidyPrescr(Boolean extraSubsidyPrescr) {
        this.extraSubsidyPrescr = extraSubsidyPrescr;
    }

    public Boolean isMedicalSubsidyPrescr() {
        return medicalSubsidyPrescr;
    }

    public PphMedicine medicalSubsidyPrescr(Boolean medicalSubsidyPrescr) {
        this.medicalSubsidyPrescr = medicalSubsidyPrescr;
        return this;
    }

    public void setMedicalSubsidyPrescr(Boolean medicalSubsidyPrescr) {
        this.medicalSubsidyPrescr = medicalSubsidyPrescr;
    }

    public Double getSubstancePrice() {
        return substancePrice;
    }

    public PphMedicine substancePrice(Double substancePrice) {
        this.substancePrice = substancePrice;
        return this;
    }

    public void setSubstancePrice(Double substancePrice) {
        this.substancePrice = substancePrice;
    }

    public Integer getIncludeInHc2() {
        return includeInHc2;
    }

    public PphMedicine includeInHc2(Integer includeInHc2) {
        this.includeInHc2 = includeInHc2;
        return this;
    }

    public void setIncludeInHc2(Integer includeInHc2) {
        this.includeInHc2 = includeInHc2;
    }

    public PrescriptionType getPrescriptionType() {
        return prescriptionType;
    }

    public PphMedicine prescriptionType(PrescriptionType prescriptionType) {
        this.prescriptionType = prescriptionType;
        return this;
    }

    public void setPrescriptionType(PrescriptionType prescriptionType) {
        this.prescriptionType = prescriptionType;
    }

    public MedicineType getMedicineType() {
        return medicineType;
    }

    public PphMedicine medicineType(MedicineType medicineType) {
        this.medicineType = medicineType;
        return this;
    }

    public void setMedicineType(MedicineType medicineType) {
        this.medicineType = medicineType;
    }

    public MedicineStatus getMedicineStatus() {
        return medicineStatus;
    }

    public PphMedicine medicineStatus(MedicineStatus medicineStatus) {
        this.medicineStatus = medicineStatus;
        return this;
    }

    public void setMedicineStatus(MedicineStatus medicineStatus) {
        this.medicineStatus = medicineStatus;
    }

    public Boolean isNormative() {
        return normative;
    }

    public PphMedicine normative(Boolean normative) {
        this.normative = normative;
        return this;
    }

    public void setNormative(Boolean normative) {
        this.normative = normative;
    }

    public Long getOgyiId() {
        return ogyiId;
    }

    public PphMedicine ogyiId(Long ogyiId) {
        this.ogyiId = ogyiId;
        return this;
    }

    public void setOgyiId(Long ogyiId) {
        this.ogyiId = ogyiId;
    }

    public Integer getNormFixGroupId() {
        return normFixGroupId;
    }

    public PphMedicine normFixGroupId(Integer normFixGroupId) {
        this.normFixGroupId = normFixGroupId;
        return this;
    }

    public void setNormFixGroupId(Integer normFixGroupId) {
        this.normFixGroupId = normFixGroupId;
    }

    public Integer getExtraSubsidyFixGroupId() {
        return extraSubsidyFixGroupId;
    }

    public PphMedicine extraSubsidyFixGroupId(Integer extraSubsidyFixGroupId) {
        this.extraSubsidyFixGroupId = extraSubsidyFixGroupId;
        return this;
    }

    public void setExtraSubsidyFixGroupId(Integer extraSubsidyFixGroupId) {
        this.extraSubsidyFixGroupId = extraSubsidyFixGroupId;
    }

    public Integer getMedicalSubsidyPrescriptionFixGroupId() {
        return medicalSubsidyPrescriptionFixGroupId;
    }

    public PphMedicine medicalSubsidyPrescriptionFixGroupId(Integer medicalSubsidyPrescriptionFixGroupId) {
        this.medicalSubsidyPrescriptionFixGroupId = medicalSubsidyPrescriptionFixGroupId;
        return this;
    }

    public void setMedicalSubsidyPrescriptionFixGroupId(Integer medicalSubsidyPrescriptionFixGroupId) {
        this.medicalSubsidyPrescriptionFixGroupId = medicalSubsidyPrescriptionFixGroupId;
    }

    public String getDosageMod() {
        return dosageMod;
    }

    public PphMedicine dosageMod(String dosageMod) {
        this.dosageMod = dosageMod;
        return this;
    }

    public void setDosageMod(String dosageMod) {
        this.dosageMod = dosageMod;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public PphMedicine activePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
        return this;
    }

    public void setActivePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
    }

    public Set<PphSubsidy> getSubSidies() {
        return subSidies;
    }

    public PphMedicine subSidies(Set<PphSubsidy> pphSubsidies) {
        this.subSidies = pphSubsidies;
        return this;
    }

    public PphMedicine addSubSidies(PphSubsidy pphSubsidy) {
        this.subSidies.add(pphSubsidy);
        pphSubsidy.setMedicine(this);
        return this;
    }

    public PphMedicine removeSubSidies(PphSubsidy pphSubsidy) {
        this.subSidies.remove(pphSubsidy);
        pphSubsidy.setMedicine(null);
        return this;
    }

    public void setSubSidies(Set<PphSubsidy> pphSubsidies) {
        this.subSidies = pphSubsidies;
    }

    public PphCompany getDealerId() {
        return dealerId;
    }

    public PphMedicine dealerId(PphCompany pphCompany) {
        this.dealerId = pphCompany;
        return this;
    }

    public void setDealerId(PphCompany pphCompany) {
        this.dealerId = pphCompany;
    }

    public PphCompany getMarketingAuthOwner() {
        return marketingAuthOwner;
    }

    public PphMedicine marketingAuthOwner(PphCompany pphCompany) {
        this.marketingAuthOwner = pphCompany;
        return this;
    }

    public void setMarketingAuthOwner(PphCompany pphCompany) {
        this.marketingAuthOwner = pphCompany;
    }

    public PphMedicineForm getMedicineForm() {
        return medicineForm;
    }

    public PphMedicine medicineForm(PphMedicineForm pphMedicineForm) {
        this.medicineForm = pphMedicineForm;
        return this;
    }

    public void setMedicineForm(PphMedicineForm pphMedicineForm) {
        this.medicineForm = pphMedicineForm;
    }

    public PphMotivationGroup getMotivationGroup() {
        return motivationGroup;
    }

    public PphMedicine motivationGroup(PphMotivationGroup pphMotivationGroup) {
        this.motivationGroup = pphMotivationGroup;
        return this;
    }

    public void setMotivationGroup(PphMotivationGroup pphMotivationGroup) {
        this.motivationGroup = pphMotivationGroup;
    }

    public PphNiche getNiche() {
        return niche;
    }

    public PphMedicine niche(PphNiche pphNiche) {
        this.niche = pphNiche;
        return this;
    }

    public void setNiche(PphNiche pphNiche) {
        this.niche = pphNiche;
    }

    public Set<PphEuScore> getEuScores() {
        return euScores;
    }

    public PphMedicine euScores(Set<PphEuScore> pphEuScores) {
        this.euScores = pphEuScores;
        return this;
    }

    public PphMedicine addEuScores(PphEuScore pphEuScore) {
        this.euScores.add(pphEuScore);
        pphEuScore.getMedicines().add(this);
        return this;
    }

    public PphMedicine removeEuScores(PphEuScore pphEuScore) {
        this.euScores.remove(pphEuScore);
        pphEuScore.getMedicines().remove(this);
        return this;
    }

    public void setEuScores(Set<PphEuScore> pphEuScores) {
        this.euScores = pphEuScores;
    }

    public PphMedicineQualifiedName getQualifiedName() {
        return qualifiedName;
    }

    public PphMedicine qualifiedName(PphMedicineQualifiedName pphMedicineQualifiedName) {
        this.qualifiedName = pphMedicineQualifiedName;
        return this;
    }

    public void setQualifiedName(PphMedicineQualifiedName pphMedicineQualifiedName) {
        this.qualifiedName = pphMedicineQualifiedName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PphMedicine)) {
            return false;
        }
        return id != null && id.equals(((PphMedicine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphMedicine{" +
            "id=" + getId() +
            ", productId=" + getProductId() +
            ", masterBookId='" + getMasterBookId() + "'" +
            ", name='" + getName() + "'" +
            ", packageDescription='" + getPackageDescription() + "'" +
            ", eanCode='" + getEanCode() + "'" +
            ", atcCode='" + getAtcCode() + "'" +
            ", brand='" + getBrand() + "'" +
            ", activeSubstance='" + getActiveSubstance() + "'" +
            ", potency='" + getPotency() + "'" +
            ", substanceAmountTotal=" + getSubstanceAmountTotal() +
            ", doseInPackage=" + getDoseInPackage() +
            ", substanceAmount=" + getSubstanceAmount() +
            ", doseMsmUnit='" + getDoseMsmUnit() + "'" +
            ", substanceMsmUnit='" + getSubstanceMsmUnit() + "'" +
            ", packageSize=" + getPackageSize() +
            ", packageMsmUnit='" + getPackageMsmUnit() + "'" +
            ", dailyDoze=" + getDailyDoze() +
            ", dailyDozeMsmUnit='" + getDailyDozeMsmUnit() + "'" +
            ", ddMsmUnitFactor=" + getDdMsmUnitFactor() +
            ", daysOfTherapy=" + getDaysOfTherapy() +
            ", consumerPrice=" + getConsumerPrice() +
            ", grossConsumerPrice=" + getGrossConsumerPrice() +
            ", efficacy='" + getEfficacy() + "'" +
            ", oldName='" + getOldName() + "'" +
            ", oepTtt='" + getOepTtt() + "'" +
            ", masterBookDeleted='" + isMasterBookDeleted() + "'" +
            ", mbookDeletedDate='" + getMbookDeletedDate() + "'" +
            ", available='" + isAvailable() + "'" +
            ", motivationStatus=" + getMotivationStatus() +
            ", costEffectivityCode=" + getCostEffectivityCode() +
            ", dailyTherapyCost=" + getDailyTherapyCost() +
            ", equivalenceGroupId=" + getEquivalenceGroupId() +
            ", oldSubsidyType='" + getOldSubsidyType() + "'" +
            ", preferredPriceFlag=" + getPreferredPriceFlag() +
            ", pharmacyFlag='" + getPharmacyFlag() + "'" +
            ", customMade='" + isCustomMade() + "'" +
            ", medicalDeviceIso='" + getMedicalDeviceIso() + "'" +
            ", producerPrice=" + getProducerPrice() +
            ", wholesalePrice=" + getWholesalePrice() +
            ", vatRate=" + getVatRate() +
            ", armyPrescription='" + isArmyPrescription() + "'" +
            ", publicSupply='" + isPublicSupply() + "'" +
            ", workAccidentPrescr='" + isWorkAccidentPrescr() + "'" +
            ", extraSubsidyPrescr='" + isExtraSubsidyPrescr() + "'" +
            ", medicalSubsidyPrescr='" + isMedicalSubsidyPrescr() + "'" +
            ", substancePrice=" + getSubstancePrice() +
            ", includeInHc2=" + getIncludeInHc2() +
            ", prescriptionType='" + getPrescriptionType() + "'" +
            ", medicineType='" + getMedicineType() + "'" +
            ", medicineStatus='" + getMedicineStatus() + "'" +
            ", normative='" + isNormative() + "'" +
            ", ogyiId=" + getOgyiId() +
            ", normFixGroupId=" + getNormFixGroupId() +
            ", extraSubsidyFixGroupId=" + getExtraSubsidyFixGroupId() +
            ", medicalSubsidyPrescriptionFixGroupId=" + getMedicalSubsidyPrescriptionFixGroupId() +
            ", dosageMod='" + getDosageMod() + "'" +
            ", activePuphaData='" + isActivePuphaData() + "'" +
            "}";
    }
}
