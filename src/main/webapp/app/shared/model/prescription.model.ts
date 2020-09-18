import { Moment } from 'moment';
import { DoctorQualificationValidatorRuleType } from 'app/shared/model/enumerations/doctor-qualification-validator-rule-type.model';
import { PrescriptionSubsidyCategory } from 'app/shared/model/enumerations/prescription-subsidy-category.model';
import { PrescriptionStatus } from 'app/shared/model/enumerations/prescription-status.model';
import { EesztSendingStatus } from 'app/shared/model/enumerations/eeszt-sending-status.model';
import { MedicalProductType } from 'app/shared/model/enumerations/medical-product-type.model';
import { Frequency } from 'app/shared/model/enumerations/frequency.model';

export interface IPrescription {
  id?: number;
  openedPackage?: boolean;
  medicalAidTeachPrescribing?: boolean;
  forH2cCertificate?: boolean;
  inscriptionDate?: Moment;
  causeOfNotReplaceability?: string;
  description?: string;
  doseMsmUnit?: string;
  eesztGroupId?: string;
  institution?: string;
  instructions?: string;
  integrationId?: string;
  equippedWith?: number;
  acceptedQualificationRule?: DoctorQualificationValidatorRuleType;
  subsidyCategory?: PrescriptionSubsidyCategory;
  status?: PrescriptionStatus;
  eesztSendingStatus?: EesztSendingStatus;
  medicalProductType?: MedicalProductType;
  preparationDescription?: string;
  invalidationReason?: string;
  frequency?: Frequency;
  frequencyMultiplier?: number;
  quantityMultiplier?: number;
  morning?: number;
  noon?: number;
  evening?: number;
  beforeSleep?: number;
  dosagePatternText?: string;
  quantity?: number;
  quantityCause?: string;
  forSeveralMonths?: boolean;
  monthsSuppliedFor?: number;
  forOnePrescription?: boolean;
  specialistRegNo?: string;
  proposalDate?: Moment;
  diagnosisId?: number;
  inscriberDoctorId?: number;
  qualificationRuleAcceptorId?: number;
  medicineId?: number;
  medicalCaseId?: number;
  puphaVersionId?: number;
}

export class Prescription implements IPrescription {
  constructor(
    public id?: number,
    public openedPackage?: boolean,
    public medicalAidTeachPrescribing?: boolean,
    public forH2cCertificate?: boolean,
    public inscriptionDate?: Moment,
    public causeOfNotReplaceability?: string,
    public description?: string,
    public doseMsmUnit?: string,
    public eesztGroupId?: string,
    public institution?: string,
    public instructions?: string,
    public integrationId?: string,
    public equippedWith?: number,
    public acceptedQualificationRule?: DoctorQualificationValidatorRuleType,
    public subsidyCategory?: PrescriptionSubsidyCategory,
    public status?: PrescriptionStatus,
    public eesztSendingStatus?: EesztSendingStatus,
    public medicalProductType?: MedicalProductType,
    public preparationDescription?: string,
    public invalidationReason?: string,
    public frequency?: Frequency,
    public frequencyMultiplier?: number,
    public quantityMultiplier?: number,
    public morning?: number,
    public noon?: number,
    public evening?: number,
    public beforeSleep?: number,
    public dosagePatternText?: string,
    public quantity?: number,
    public quantityCause?: string,
    public forSeveralMonths?: boolean,
    public monthsSuppliedFor?: number,
    public forOnePrescription?: boolean,
    public specialistRegNo?: string,
    public proposalDate?: Moment,
    public diagnosisId?: number,
    public inscriberDoctorId?: number,
    public qualificationRuleAcceptorId?: number,
    public medicineId?: number,
    public medicalCaseId?: number,
    public puphaVersionId?: number
  ) {
    this.openedPackage = this.openedPackage || false;
    this.medicalAidTeachPrescribing = this.medicalAidTeachPrescribing || false;
    this.forH2cCertificate = this.forH2cCertificate || false;
    this.forSeveralMonths = this.forSeveralMonths || false;
    this.forOnePrescription = this.forOnePrescription || false;
  }
}
