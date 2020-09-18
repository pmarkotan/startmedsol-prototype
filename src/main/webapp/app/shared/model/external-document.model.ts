import { Moment } from 'moment';

export interface IExternalDocument {
  id?: number;
  documentId?: string;
  name?: string;
  created?: Moment;
  uploaded?: Moment;
  medicalCaseId?: number;
}

export class ExternalDocument implements IExternalDocument {
  constructor(
    public id?: number,
    public documentId?: string,
    public name?: string,
    public created?: Moment,
    public uploaded?: Moment,
    public medicalCaseId?: number
  ) {}
}
