import { MedicalServiceUnit } from 'app/shared/model/enumerations/medical-service-unit.model';
import { TaxRate } from 'app/shared/model/enumerations/tax-rate.model';

export interface IMedicalService {
  id?: number;
  code?: string;
  name?: string;
  grossPrice?: number;
  unit?: MedicalServiceUnit;
  statisticalCode?: string;
  taxRate?: TaxRate;
  praxisId?: number;
  providerId?: number;
}

export class MedicalService implements IMedicalService {
  constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public grossPrice?: number,
    public unit?: MedicalServiceUnit,
    public statisticalCode?: string,
    public taxRate?: TaxRate,
    public praxisId?: number,
    public providerId?: number
  ) {}
}
