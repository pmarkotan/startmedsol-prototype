export interface ICompany {
  id?: number;
  registrationNumber?: string;
  workplaceName?: string;
  workplaceId?: string;
}

export class Company implements ICompany {
  constructor(public id?: number, public registrationNumber?: string, public workplaceName?: string, public workplaceId?: string) {}
}
