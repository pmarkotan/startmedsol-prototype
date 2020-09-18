import { PcRange } from 'app/shared/model/enumerations/pc-range.model';

export interface ICsPostalCode {
  id?: number;
  code?: string;
  settlement?: string;
  part?: string;
  street?: string;
  kind?: string;
  rangeType?: PcRange;
  rangeLo?: number;
  rangeHi?: number;
  district?: string;
  validityId?: number;
}

export class CsPostalCode implements ICsPostalCode {
  constructor(
    public id?: number,
    public code?: string,
    public settlement?: string,
    public part?: string,
    public street?: string,
    public kind?: string,
    public rangeType?: PcRange,
    public rangeLo?: number,
    public rangeHi?: number,
    public district?: string,
    public validityId?: number
  ) {}
}
