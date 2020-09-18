export interface IBillingInformation {
  id?: number;
  name?: string;
  taxnumber?: string;
  billingAddressId?: number;
}

export class BillingInformation implements IBillingInformation {
  constructor(public id?: number, public name?: string, public taxnumber?: string, public billingAddressId?: number) {}
}
