import { Moment } from 'moment';
import { IPphSubsidy } from 'app/shared/model/pph-subsidy.model';
import { IPphEuScore } from 'app/shared/model/pph-eu-score.model';
import { PrescriptionType } from 'app/shared/model/enumerations/prescription-type.model';
import { MedicineType } from 'app/shared/model/enumerations/medicine-type.model';
import { MedicineStatus } from 'app/shared/model/enumerations/medicine-status.model';

export interface IPphMedicine {
  id?: number;
  productId?: number;
  masterBookId?: string;
  name?: string;
  packageDescription?: string;
  eanCode?: string;
  atcCode?: string;
  brand?: string;
  activeSubstance?: string;
  potency?: string;
  substanceAmountTotal?: number;
  doseInPackage?: number;
  substanceAmount?: number;
  doseMsmUnit?: string;
  substanceMsmUnit?: string;
  packageSize?: number;
  packageMsmUnit?: string;
  dailyDoze?: number;
  dailyDozeMsmUnit?: string;
  ddMsmUnitFactor?: number;
  daysOfTherapy?: number;
  consumerPrice?: number;
  grossConsumerPrice?: number;
  efficacy?: string;
  oldName?: string;
  oepTtt?: string;
  masterBookDeleted?: boolean;
  mbookDeletedDate?: Moment;
  available?: boolean;
  motivationStatus?: number;
  costEffectivityCode?: number;
  dailyTherapyCost?: number;
  equivalenceGroupId?: number;
  oldSubsidyType?: string;
  preferredPriceFlag?: number;
  pharmacyFlag?: string;
  customMade?: boolean;
  medicalDeviceIso?: string;
  producerPrice?: number;
  wholesalePrice?: number;
  vatRate?: number;
  armyPrescription?: boolean;
  publicSupply?: boolean;
  workAccidentPrescr?: boolean;
  extraSubsidyPrescr?: boolean;
  medicalSubsidyPrescr?: boolean;
  substancePrice?: number;
  includeInHc2?: number;
  prescriptionType?: PrescriptionType;
  medicineType?: MedicineType;
  medicineStatus?: MedicineStatus;
  normative?: boolean;
  ogyiId?: number;
  normFixGroupId?: number;
  extraSubsidyFixGroupId?: number;
  medicalSubsidyPrescriptionFixGroupId?: number;
  dosageMod?: string;
  activePuphaData?: boolean;
  subSidies?: IPphSubsidy[];
  dealerIdId?: number;
  marketingAuthOwnerId?: number;
  medicineFormId?: number;
  motivationGroupId?: number;
  nicheId?: number;
  euScores?: IPphEuScore[];
  qualifiedNameId?: number;
}

export class PphMedicine implements IPphMedicine {
  constructor(
    public id?: number,
    public productId?: number,
    public masterBookId?: string,
    public name?: string,
    public packageDescription?: string,
    public eanCode?: string,
    public atcCode?: string,
    public brand?: string,
    public activeSubstance?: string,
    public potency?: string,
    public substanceAmountTotal?: number,
    public doseInPackage?: number,
    public substanceAmount?: number,
    public doseMsmUnit?: string,
    public substanceMsmUnit?: string,
    public packageSize?: number,
    public packageMsmUnit?: string,
    public dailyDoze?: number,
    public dailyDozeMsmUnit?: string,
    public ddMsmUnitFactor?: number,
    public daysOfTherapy?: number,
    public consumerPrice?: number,
    public grossConsumerPrice?: number,
    public efficacy?: string,
    public oldName?: string,
    public oepTtt?: string,
    public masterBookDeleted?: boolean,
    public mbookDeletedDate?: Moment,
    public available?: boolean,
    public motivationStatus?: number,
    public costEffectivityCode?: number,
    public dailyTherapyCost?: number,
    public equivalenceGroupId?: number,
    public oldSubsidyType?: string,
    public preferredPriceFlag?: number,
    public pharmacyFlag?: string,
    public customMade?: boolean,
    public medicalDeviceIso?: string,
    public producerPrice?: number,
    public wholesalePrice?: number,
    public vatRate?: number,
    public armyPrescription?: boolean,
    public publicSupply?: boolean,
    public workAccidentPrescr?: boolean,
    public extraSubsidyPrescr?: boolean,
    public medicalSubsidyPrescr?: boolean,
    public substancePrice?: number,
    public includeInHc2?: number,
    public prescriptionType?: PrescriptionType,
    public medicineType?: MedicineType,
    public medicineStatus?: MedicineStatus,
    public normative?: boolean,
    public ogyiId?: number,
    public normFixGroupId?: number,
    public extraSubsidyFixGroupId?: number,
    public medicalSubsidyPrescriptionFixGroupId?: number,
    public dosageMod?: string,
    public activePuphaData?: boolean,
    public subSidies?: IPphSubsidy[],
    public dealerIdId?: number,
    public marketingAuthOwnerId?: number,
    public medicineFormId?: number,
    public motivationGroupId?: number,
    public nicheId?: number,
    public euScores?: IPphEuScore[],
    public qualifiedNameId?: number
  ) {
    this.masterBookDeleted = this.masterBookDeleted || false;
    this.available = this.available || false;
    this.customMade = this.customMade || false;
    this.armyPrescription = this.armyPrescription || false;
    this.publicSupply = this.publicSupply || false;
    this.workAccidentPrescr = this.workAccidentPrescr || false;
    this.extraSubsidyPrescr = this.extraSubsidyPrescr || false;
    this.medicalSubsidyPrescr = this.medicalSubsidyPrescr || false;
    this.normative = this.normative || false;
    this.activePuphaData = this.activePuphaData || false;
  }
}
