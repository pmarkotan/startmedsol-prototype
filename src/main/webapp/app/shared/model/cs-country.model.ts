export interface ICsCountry {
  id?: number;
  code?: string;
  description?: string;
  validityId?: number;
}

export class CsCountry implements ICsCountry {
  constructor(public id?: number, public code?: string, public description?: string, public validityId?: number) {}
}
