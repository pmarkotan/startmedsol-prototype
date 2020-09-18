import { PatientDocumentationType } from 'app/shared/model/enumerations/patient-documentation-type.model';

export interface ICaseTextDocumentation {
  id?: number;
  text?: any;
  type?: PatientDocumentationType;
}

export class CaseTextDocumentation implements ICaseTextDocumentation {
  constructor(public id?: number, public text?: any, public type?: PatientDocumentationType) {}
}
