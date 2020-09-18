export interface IPerformedProcedure {
  id?: number;
  procedureId?: number;
  medicalCaseId?: number;
}

export class PerformedProcedure implements IPerformedProcedure {
  constructor(public id?: number, public procedureId?: number, public medicalCaseId?: number) {}
}
