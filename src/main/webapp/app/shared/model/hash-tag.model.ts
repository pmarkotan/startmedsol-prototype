import { IPatient } from 'app/shared/model/patient.model';

export interface IHashTag {
  id?: number;
  name?: string;
  providerNameLong?: string;
  providerId?: number;
  patients?: IPatient[];
}

export class HashTag implements IHashTag {
  constructor(
    public id?: number,
    public name?: string,
    public providerNameLong?: string,
    public providerId?: number,
    public patients?: IPatient[]
  ) {}
}
