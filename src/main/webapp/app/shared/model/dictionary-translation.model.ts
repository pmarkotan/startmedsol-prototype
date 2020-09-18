import { Language } from 'app/shared/model/enumerations/language.model';

export interface IDictionaryTranslation {
  id?: number;
  label?: string;
  language?: Language;
  dictionaryItemId?: number;
}

export class DictionaryTranslation implements IDictionaryTranslation {
  constructor(public id?: number, public label?: string, public language?: Language, public dictionaryItemId?: number) {}
}
