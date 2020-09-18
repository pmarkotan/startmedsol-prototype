import { IPphMedicine } from 'app/shared/model/pph-medicine.model';

export interface IPphMedicineQualifiedName {
  id?: number;
  name?: string;
  activeSubstance?: string;
  activePuphaData?: boolean;
  medicines?: IPphMedicine[];
}

export class PphMedicineQualifiedName implements IPphMedicineQualifiedName {
  constructor(
    public id?: number,
    public name?: string,
    public activeSubstance?: string,
    public activePuphaData?: boolean,
    public medicines?: IPphMedicine[]
  ) {
    this.activePuphaData = this.activePuphaData || false;
  }
}
