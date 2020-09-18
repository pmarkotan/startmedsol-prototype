import { DgSex } from 'app/shared/model/enumerations/dg-sex.model';

export interface ICsDiagnosis {
  id?: number;
  code?: string;
  report?: boolean;
  descr?: string;
  sex?: DgSex;
  ageMin?: number;
  ageMax?: number;
  validityId?: number;
}

export class CsDiagnosis implements ICsDiagnosis {
  constructor(
    public id?: number,
    public code?: string,
    public report?: boolean,
    public descr?: string,
    public sex?: DgSex,
    public ageMin?: number,
    public ageMax?: number,
    public validityId?: number
  ) {
    this.report = this.report || false;
  }
}
