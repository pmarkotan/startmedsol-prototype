export interface INavSettings {
  id?: number;
  technicalUserName?: string;
  technicalPassword?: string;
  signingKey?: string;
  replacementKey?: string;
}

export class NavSettings implements INavSettings {
  constructor(
    public id?: number,
    public technicalUserName?: string,
    public technicalPassword?: string,
    public signingKey?: string,
    public replacementKey?: string
  ) {}
}
