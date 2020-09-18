import { Moment } from 'moment';
import { MedicalInvoiceType } from 'app/shared/model/enumerations/medical-invoice-type.model';

export interface IMedicalInvoice {
  id?: number;
  orderNumber?: number;
  invoiceNumber?: string;
  type?: MedicalInvoiceType;
  total?: number;
  creatorUser?: string;
  createdAt?: Moment;
  medicalCaseId?: number;
}

export class MedicalInvoice implements IMedicalInvoice {
  constructor(
    public id?: number,
    public orderNumber?: number,
    public invoiceNumber?: string,
    public type?: MedicalInvoiceType,
    public total?: number,
    public creatorUser?: string,
    public createdAt?: Moment,
    public medicalCaseId?: number
  ) {}
}
