export interface IProceduresOfPraxis {
  id?: number;
  procedureId?: number;
  praxisId?: number;
}

export class ProceduresOfPraxis implements IProceduresOfPraxis {
  constructor(public id?: number, public procedureId?: number, public praxisId?: number) {}
}
