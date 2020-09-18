import { Moment } from 'moment';

export interface IEhrDocument {
  id?: number;
  eesztId?: string;
  documentId?: string;
  created?: Moment;
  documentType?: string;
  doctorName?: string;
  institutionName?: string;
  praxisName?: string;
}

export class EhrDocument implements IEhrDocument {
  constructor(
    public id?: number,
    public eesztId?: string,
    public documentId?: string,
    public created?: Moment,
    public documentType?: string,
    public doctorName?: string,
    public institutionName?: string,
    public praxisName?: string
  ) {}
}
