import { PraxisStatus } from 'app/shared/model/enumerations/praxis-status.model';

export interface IPraxis {
  id?: number;
  name?: string;
  departmentId?: string;
  professionCode?: string;
  professionName?: string;
  status?: PraxisStatus;
  appointmentPhone?: string;
  treatmentLogbookNumber?: number;
  officeAddressId?: number;
  providerId?: number;
  doctorId?: number;
}

export class Praxis implements IPraxis {
  constructor(
    public id?: number,
    public name?: string,
    public departmentId?: string,
    public professionCode?: string,
    public professionName?: string,
    public status?: PraxisStatus,
    public appointmentPhone?: string,
    public treatmentLogbookNumber?: number,
    public officeAddressId?: number,
    public providerId?: number,
    public doctorId?: number
  ) {}
}
