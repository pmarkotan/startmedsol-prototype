export interface IDynamicForm {
  id?: number;
  code?: string;
  name?: string;
  professionCode?: string;
  professionName?: string;
  formTemplate?: any;
  reportTemplate?: any;
  separatelyPrint?: boolean;
  providerId?: number;
}

export class DynamicForm implements IDynamicForm {
  constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public professionCode?: string,
    public professionName?: string,
    public formTemplate?: any,
    public reportTemplate?: any,
    public separatelyPrint?: boolean,
    public providerId?: number
  ) {
    this.separatelyPrint = this.separatelyPrint || false;
  }
}
