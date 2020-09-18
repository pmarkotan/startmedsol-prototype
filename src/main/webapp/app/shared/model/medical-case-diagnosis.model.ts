export interface IMedicalCaseDiagnosis {
  id?: number;
  diagnosisId?: number;
  medicalCaseId?: number;
}

export class MedicalCaseDiagnosis implements IMedicalCaseDiagnosis {
  constructor(public id?: number, public diagnosisId?: number, public medicalCaseId?: number) {}
}
