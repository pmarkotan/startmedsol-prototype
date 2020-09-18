export interface IDataGenerator {
  id?: number;
  provider?: number;
  praxis?: number;
  patient?: number;
  medicalCase?: number;
}

export class DataGenerator implements IDataGenerator {
  constructor(public id?: number, public provider?: number, public praxis?: number, public patient?: number, public medicalCase?: number) {}
}
