package hu.paninform.startmedsol.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import hu.paninform.startmedsol.domain.enumeration.PrescriptionType;
import hu.paninform.startmedsol.domain.enumeration.MedicineType;
import hu.paninform.startmedsol.domain.enumeration.MedicineStatus;

/**
 * A DTO for the {@link hu.paninform.startmedsol.domain.PphMedicine} entity.
 */
public class PphMedicineDTO implements Serializable {
    
    private Long id;

    private Long productId;

    @Size(max = 64)
    private String masterBookId;

    @Size(max = 256)
    private String name;

    @Size(max = 256)
    private String packageDescription;

    @Size(max = 16)
    private String eanCode;

    @Size(max = 8)
    private String atcCode;

    @Size(max = 128)
    private String brand;

    @Size(max = 128)
    private String activeSubstance;

    @Size(max = 64)
    private String potency;

    private Double substanceAmountTotal;

    private Integer doseInPackage;

    private Double substanceAmount;

    @Size(max = 32)
    private String doseMsmUnit;

    @Size(max = 50)
    private String substanceMsmUnit;

    private Integer packageSize;

    @Size(max = 50)
    private String packageMsmUnit;

    private Double dailyDoze;

    @Size(max = 50)
    private String dailyDozeMsmUnit;

    private Double ddMsmUnitFactor;

    private Integer daysOfTherapy;

    private Double consumerPrice;

    private Double grossConsumerPrice;

    @Size(max = 32)
    private String efficacy;

    @Size(max = 256)
    private String oldName;

    @Size(max = 9)
    private String oepTtt;

    private Boolean masterBookDeleted;

    private Instant mbookDeletedDate;

    private Boolean available;

    private Integer motivationStatus;

    private Integer costEffectivityCode;

    private Double dailyTherapyCost;

    private Integer equivalenceGroupId;

    @Size(max = 4)
    private String oldSubsidyType;

    private Integer preferredPriceFlag;

    @Size(max = 2)
    private String pharmacyFlag;

    private Boolean customMade;

    @Size(max = 16)
    private String medicalDeviceIso;

    private Double producerPrice;

    private Double wholesalePrice;

    private Double vatRate;

    private Boolean armyPrescription;

    private Boolean publicSupply;

    private Boolean workAccidentPrescr;

    private Boolean extraSubsidyPrescr;

    private Boolean medicalSubsidyPrescr;

    private Double substancePrice;

    private Integer includeInHc2;

    private PrescriptionType prescriptionType;

    private MedicineType medicineType;

    private MedicineStatus medicineStatus;

    private Boolean normative;

    private Long ogyiId;

    private Integer normFixGroupId;

    private Integer extraSubsidyFixGroupId;

    private Integer medicalSubsidyPrescriptionFixGroupId;

    @Size(max = 256)
    private String dosageMod;

    @NotNull
    private Boolean activePuphaData;


    private Long dealerIdId;

    private Long marketingAuthOwnerId;

    private Long medicineFormId;

    private Long motivationGroupId;

    private Long nicheId;
    private Set<PphEuScoreDTO> euScores = new HashSet<>();

    private Long qualifiedNameId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getMasterBookId() {
        return masterBookId;
    }

    public void setMasterBookId(String masterBookId) {
        this.masterBookId = masterBookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public String getEanCode() {
        return eanCode;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    public String getAtcCode() {
        return atcCode;
    }

    public void setAtcCode(String atcCode) {
        this.atcCode = atcCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public String getPotency() {
        return potency;
    }

    public void setPotency(String potency) {
        this.potency = potency;
    }

    public Double getSubstanceAmountTotal() {
        return substanceAmountTotal;
    }

    public void setSubstanceAmountTotal(Double substanceAmountTotal) {
        this.substanceAmountTotal = substanceAmountTotal;
    }

    public Integer getDoseInPackage() {
        return doseInPackage;
    }

    public void setDoseInPackage(Integer doseInPackage) {
        this.doseInPackage = doseInPackage;
    }

    public Double getSubstanceAmount() {
        return substanceAmount;
    }

    public void setSubstanceAmount(Double substanceAmount) {
        this.substanceAmount = substanceAmount;
    }

    public String getDoseMsmUnit() {
        return doseMsmUnit;
    }

    public void setDoseMsmUnit(String doseMsmUnit) {
        this.doseMsmUnit = doseMsmUnit;
    }

    public String getSubstanceMsmUnit() {
        return substanceMsmUnit;
    }

    public void setSubstanceMsmUnit(String substanceMsmUnit) {
        this.substanceMsmUnit = substanceMsmUnit;
    }

    public Integer getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(Integer packageSize) {
        this.packageSize = packageSize;
    }

    public String getPackageMsmUnit() {
        return packageMsmUnit;
    }

    public void setPackageMsmUnit(String packageMsmUnit) {
        this.packageMsmUnit = packageMsmUnit;
    }

    public Double getDailyDoze() {
        return dailyDoze;
    }

    public void setDailyDoze(Double dailyDoze) {
        this.dailyDoze = dailyDoze;
    }

    public String getDailyDozeMsmUnit() {
        return dailyDozeMsmUnit;
    }

    public void setDailyDozeMsmUnit(String dailyDozeMsmUnit) {
        this.dailyDozeMsmUnit = dailyDozeMsmUnit;
    }

    public Double getDdMsmUnitFactor() {
        return ddMsmUnitFactor;
    }

    public void setDdMsmUnitFactor(Double ddMsmUnitFactor) {
        this.ddMsmUnitFactor = ddMsmUnitFactor;
    }

    public Integer getDaysOfTherapy() {
        return daysOfTherapy;
    }

    public void setDaysOfTherapy(Integer daysOfTherapy) {
        this.daysOfTherapy = daysOfTherapy;
    }

    public Double getConsumerPrice() {
        return consumerPrice;
    }

    public void setConsumerPrice(Double consumerPrice) {
        this.consumerPrice = consumerPrice;
    }

    public Double getGrossConsumerPrice() {
        return grossConsumerPrice;
    }

    public void setGrossConsumerPrice(Double grossConsumerPrice) {
        this.grossConsumerPrice = grossConsumerPrice;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getOepTtt() {
        return oepTtt;
    }

    public void setOepTtt(String oepTtt) {
        this.oepTtt = oepTtt;
    }

    public Boolean isMasterBookDeleted() {
        return masterBookDeleted;
    }

    public void setMasterBookDeleted(Boolean masterBookDeleted) {
        this.masterBookDeleted = masterBookDeleted;
    }

    public Instant getMbookDeletedDate() {
        return mbookDeletedDate;
    }

    public void setMbookDeletedDate(Instant mbookDeletedDate) {
        this.mbookDeletedDate = mbookDeletedDate;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getMotivationStatus() {
        return motivationStatus;
    }

    public void setMotivationStatus(Integer motivationStatus) {
        this.motivationStatus = motivationStatus;
    }

    public Integer getCostEffectivityCode() {
        return costEffectivityCode;
    }

    public void setCostEffectivityCode(Integer costEffectivityCode) {
        this.costEffectivityCode = costEffectivityCode;
    }

    public Double getDailyTherapyCost() {
        return dailyTherapyCost;
    }

    public void setDailyTherapyCost(Double dailyTherapyCost) {
        this.dailyTherapyCost = dailyTherapyCost;
    }

    public Integer getEquivalenceGroupId() {
        return equivalenceGroupId;
    }

    public void setEquivalenceGroupId(Integer equivalenceGroupId) {
        this.equivalenceGroupId = equivalenceGroupId;
    }

    public String getOldSubsidyType() {
        return oldSubsidyType;
    }

    public void setOldSubsidyType(String oldSubsidyType) {
        this.oldSubsidyType = oldSubsidyType;
    }

    public Integer getPreferredPriceFlag() {
        return preferredPriceFlag;
    }

    public void setPreferredPriceFlag(Integer preferredPriceFlag) {
        this.preferredPriceFlag = preferredPriceFlag;
    }

    public String getPharmacyFlag() {
        return pharmacyFlag;
    }

    public void setPharmacyFlag(String pharmacyFlag) {
        this.pharmacyFlag = pharmacyFlag;
    }

    public Boolean isCustomMade() {
        return customMade;
    }

    public void setCustomMade(Boolean customMade) {
        this.customMade = customMade;
    }

    public String getMedicalDeviceIso() {
        return medicalDeviceIso;
    }

    public void setMedicalDeviceIso(String medicalDeviceIso) {
        this.medicalDeviceIso = medicalDeviceIso;
    }

    public Double getProducerPrice() {
        return producerPrice;
    }

    public void setProducerPrice(Double producerPrice) {
        this.producerPrice = producerPrice;
    }

    public Double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(Double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public Double getVatRate() {
        return vatRate;
    }

    public void setVatRate(Double vatRate) {
        this.vatRate = vatRate;
    }

    public Boolean isArmyPrescription() {
        return armyPrescription;
    }

    public void setArmyPrescription(Boolean armyPrescription) {
        this.armyPrescription = armyPrescription;
    }

    public Boolean isPublicSupply() {
        return publicSupply;
    }

    public void setPublicSupply(Boolean publicSupply) {
        this.publicSupply = publicSupply;
    }

    public Boolean isWorkAccidentPrescr() {
        return workAccidentPrescr;
    }

    public void setWorkAccidentPrescr(Boolean workAccidentPrescr) {
        this.workAccidentPrescr = workAccidentPrescr;
    }

    public Boolean isExtraSubsidyPrescr() {
        return extraSubsidyPrescr;
    }

    public void setExtraSubsidyPrescr(Boolean extraSubsidyPrescr) {
        this.extraSubsidyPrescr = extraSubsidyPrescr;
    }

    public Boolean isMedicalSubsidyPrescr() {
        return medicalSubsidyPrescr;
    }

    public void setMedicalSubsidyPrescr(Boolean medicalSubsidyPrescr) {
        this.medicalSubsidyPrescr = medicalSubsidyPrescr;
    }

    public Double getSubstancePrice() {
        return substancePrice;
    }

    public void setSubstancePrice(Double substancePrice) {
        this.substancePrice = substancePrice;
    }

    public Integer getIncludeInHc2() {
        return includeInHc2;
    }

    public void setIncludeInHc2(Integer includeInHc2) {
        this.includeInHc2 = includeInHc2;
    }

    public PrescriptionType getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(PrescriptionType prescriptionType) {
        this.prescriptionType = prescriptionType;
    }

    public MedicineType getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(MedicineType medicineType) {
        this.medicineType = medicineType;
    }

    public MedicineStatus getMedicineStatus() {
        return medicineStatus;
    }

    public void setMedicineStatus(MedicineStatus medicineStatus) {
        this.medicineStatus = medicineStatus;
    }

    public Boolean isNormative() {
        return normative;
    }

    public void setNormative(Boolean normative) {
        this.normative = normative;
    }

    public Long getOgyiId() {
        return ogyiId;
    }

    public void setOgyiId(Long ogyiId) {
        this.ogyiId = ogyiId;
    }

    public Integer getNormFixGroupId() {
        return normFixGroupId;
    }

    public void setNormFixGroupId(Integer normFixGroupId) {
        this.normFixGroupId = normFixGroupId;
    }

    public Integer getExtraSubsidyFixGroupId() {
        return extraSubsidyFixGroupId;
    }

    public void setExtraSubsidyFixGroupId(Integer extraSubsidyFixGroupId) {
        this.extraSubsidyFixGroupId = extraSubsidyFixGroupId;
    }

    public Integer getMedicalSubsidyPrescriptionFixGroupId() {
        return medicalSubsidyPrescriptionFixGroupId;
    }

    public void setMedicalSubsidyPrescriptionFixGroupId(Integer medicalSubsidyPrescriptionFixGroupId) {
        this.medicalSubsidyPrescriptionFixGroupId = medicalSubsidyPrescriptionFixGroupId;
    }

    public String getDosageMod() {
        return dosageMod;
    }

    public void setDosageMod(String dosageMod) {
        this.dosageMod = dosageMod;
    }

    public Boolean isActivePuphaData() {
        return activePuphaData;
    }

    public void setActivePuphaData(Boolean activePuphaData) {
        this.activePuphaData = activePuphaData;
    }

    public Long getDealerIdId() {
        return dealerIdId;
    }

    public void setDealerIdId(Long pphCompanyId) {
        this.dealerIdId = pphCompanyId;
    }

    public Long getMarketingAuthOwnerId() {
        return marketingAuthOwnerId;
    }

    public void setMarketingAuthOwnerId(Long pphCompanyId) {
        this.marketingAuthOwnerId = pphCompanyId;
    }

    public Long getMedicineFormId() {
        return medicineFormId;
    }

    public void setMedicineFormId(Long pphMedicineFormId) {
        this.medicineFormId = pphMedicineFormId;
    }

    public Long getMotivationGroupId() {
        return motivationGroupId;
    }

    public void setMotivationGroupId(Long pphMotivationGroupId) {
        this.motivationGroupId = pphMotivationGroupId;
    }

    public Long getNicheId() {
        return nicheId;
    }

    public void setNicheId(Long pphNicheId) {
        this.nicheId = pphNicheId;
    }

    public Set<PphEuScoreDTO> getEuScores() {
        return euScores;
    }

    public void setEuScores(Set<PphEuScoreDTO> pphEuScores) {
        this.euScores = pphEuScores;
    }

    public Long getQualifiedNameId() {
        return qualifiedNameId;
    }

    public void setQualifiedNameId(Long pphMedicineQualifiedNameId) {
        this.qualifiedNameId = pphMedicineQualifiedNameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PphMedicineDTO)) {
            return false;
        }

        return id != null && id.equals(((PphMedicineDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PphMedicineDTO{" +
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
            ", dealerIdId=" + getDealerIdId() +
            ", marketingAuthOwnerId=" + getMarketingAuthOwnerId() +
            ", medicineFormId=" + getMedicineFormId() +
            ", motivationGroupId=" + getMotivationGroupId() +
            ", nicheId=" + getNicheId() +
            ", euScores='" + getEuScores() + "'" +
            ", qualifiedNameId=" + getQualifiedNameId() +
            "}";
    }
}
