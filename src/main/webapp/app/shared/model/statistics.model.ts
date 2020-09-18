import { Moment } from 'moment';
import { StatisticsType } from 'app/shared/model/enumerations/statistics-type.model';

export interface IStatistics {
  id?: number;
  created?: Moment;
  tpye?: StatisticsType;
  content?: string;
  description?: string;
}

export class Statistics implements IStatistics {
  constructor(
    public id?: number,
    public created?: Moment,
    public tpye?: StatisticsType,
    public content?: string,
    public description?: string
  ) {}
}
