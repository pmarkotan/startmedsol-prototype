import { IPrescription } from 'app/shared/model/prescription.model';

export interface IPrescriptionEesztId {
  id?: number;
  eesztId?: string;
  eesztVersion?: number;
  prescription?: IPrescription;
}

export class PrescriptionEesztId implements IPrescriptionEesztId {
  constructor(public id?: number, public eesztId?: string, public eesztVersion?: number, public prescription?: IPrescription) {}
}
