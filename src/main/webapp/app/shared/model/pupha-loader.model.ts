import { Moment } from 'moment';

export interface IPuphaLoader {
  id?: number;
  event?: string;
  log?: string;
  time?: Moment;
}

export class PuphaLoader implements IPuphaLoader {
  constructor(public id?: number, public event?: string, public log?: string, public time?: Moment) {}
}
