import { IPersonalData } from 'app/shared/model/personal-data.model';
import { IPraxis } from 'app/shared/model/praxis.model';
import { IProvider } from 'app/shared/model/provider.model';
import { EmployeeType } from 'app/shared/model/enumerations/employee-type.model';
import { EesztLoginType } from 'app/shared/model/enumerations/eeszt-login-type.model';

export interface IEmployee {
  id?: number;
  emlpoyeeType?: EmployeeType;
  identifier?: string;
  eesztIdentifier?: string;
  eesztLoginType?: EesztLoginType;
  personalData?: IPersonalData;
  praxes?: IPraxis[];
  providers?: IProvider[];
}

export class Employee implements IEmployee {
  constructor(
    public id?: number,
    public emlpoyeeType?: EmployeeType,
    public identifier?: string,
    public eesztIdentifier?: string,
    public eesztLoginType?: EesztLoginType,
    public personalData?: IPersonalData,
    public praxes?: IPraxis[],
    public providers?: IProvider[]
  ) {}
}
