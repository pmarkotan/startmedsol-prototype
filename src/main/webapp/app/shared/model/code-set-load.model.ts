import { CodeSetType } from 'app/shared/model/enumerations/code-set-type.model';
import { CodeSetStatus } from 'app/shared/model/enumerations/code-set-status.model';

export interface ICodeSetLoad {
  id?: number;
  type?: CodeSetType;
  latestVersion?: string;
  url?: string;
  log?: string;
  status?: CodeSetStatus;
}

export class CodeSetLoad implements ICodeSetLoad {
  constructor(
    public id?: number,
    public type?: CodeSetType,
    public latestVersion?: string,
    public url?: string,
    public log?: string,
    public status?: CodeSetStatus
  ) {}
}
