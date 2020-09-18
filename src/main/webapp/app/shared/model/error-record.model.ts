import { Moment } from 'moment';

export interface IErrorRecord {
  id?: number;
  createDate?: Moment;
  content?: any;
}

export class ErrorRecord implements IErrorRecord {
  constructor(public id?: number, public createDate?: Moment, public content?: any) {}
}
