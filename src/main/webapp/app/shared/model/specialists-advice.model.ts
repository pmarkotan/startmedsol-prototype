export interface ISpecialistsAdvice {
  id?: number;
  periodOfTime?: string;
  raisedIndicationCode?: string;
  raisedSubsidyPercent?: string;
  emphasizedIndicationCode?: string;
  activeSubstance?: string;
  efficacy?: string;
  dosageMod?: string;
  dosage?: string;
  medicalCaseId?: number;
  diagnosisId?: number;
}

export class SpecialistsAdvice implements ISpecialistsAdvice {
  constructor(
    public id?: number,
    public periodOfTime?: string,
    public raisedIndicationCode?: string,
    public raisedSubsidyPercent?: string,
    public emphasizedIndicationCode?: string,
    public activeSubstance?: string,
    public efficacy?: string,
    public dosageMod?: string,
    public dosage?: string,
    public medicalCaseId?: number,
    public diagnosisId?: number
  ) {}
}
