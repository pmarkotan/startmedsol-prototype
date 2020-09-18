export interface ICsProcedure {
  id?: number;
  code?: string;
  description?: string;
  validityId?: number;
}

export class CsProcedure implements ICsProcedure {
  constructor(public id?: number, public code?: string, public description?: string, public validityId?: number) {}
}
