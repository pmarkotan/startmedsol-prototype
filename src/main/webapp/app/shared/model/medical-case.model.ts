import { Moment } from 'moment';
import { IExternalDocument } from 'app/shared/model/external-document.model';
import { MedicalCaseStatus } from 'app/shared/model/enumerations/medical-case-status.model';
import { AttendanceType } from 'app/shared/model/enumerations/attendance-type.model';

export interface IMedicalCase {
  id?: number;
  deleted?: boolean;
  confidental?: boolean;
  ambulentNumber?: string;
  admissionDate?: Moment;
  closeDate?: Moment;
  status?: MedicalCaseStatus;
  attendanceType?: AttendanceType;
  documents?: IExternalDocument[];
}

export class MedicalCase implements IMedicalCase {
  constructor(
    public id?: number,
    public deleted?: boolean,
    public confidental?: boolean,
    public ambulentNumber?: string,
    public admissionDate?: Moment,
    public closeDate?: Moment,
    public status?: MedicalCaseStatus,
    public attendanceType?: AttendanceType,
    public documents?: IExternalDocument[]
  ) {
    this.deleted = this.deleted || false;
    this.confidental = this.confidental || false;
  }
}
