import { IPraxis } from 'app/shared/model/praxis.model';
import { IEmployee } from 'app/shared/model/employee.model';

export interface IProvider {
  id?: number;
  nameLong?: string;
  nameShort?: string;
  institutionId?: string;
  email?: string;
  phone?: string;
  accountNumber?: string;
  contactPersonId?: number;
  companyId?: number;
  billingInformationId?: number;
  praxes?: IPraxis[];
  employees?: IEmployee[];
}

export class Provider implements IProvider {
  constructor(
    public id?: number,
    public nameLong?: string,
    public nameShort?: string,
    public institutionId?: string,
    public email?: string,
    public phone?: string,
    public accountNumber?: string,
    public contactPersonId?: number,
    public companyId?: number,
    public billingInformationId?: number,
    public praxes?: IPraxis[],
    public employees?: IEmployee[]
  ) {}
}
