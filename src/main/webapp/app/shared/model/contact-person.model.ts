export interface IContactPerson {
  id?: number;
  name?: string;
  phone?: string;
  email?: string;
}

export class ContactPerson implements IContactPerson {
  constructor(public id?: number, public name?: string, public phone?: string, public email?: string) {}
}
