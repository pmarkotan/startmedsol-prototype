import { IDictionaryTranslation } from 'app/shared/model/dictionary-translation.model';

export interface IDictionaryItem {
  id?: number;
  dictionaryItemType?: string;
  code?: string;
  orderNumber?: number;
  description?: string;
  dictionaryTranslations?: IDictionaryTranslation[];
  parents?: IDictionaryItem[];
  children?: IDictionaryItem[];
}

export class DictionaryItem implements IDictionaryItem {
  constructor(
    public id?: number,
    public dictionaryItemType?: string,
    public code?: string,
    public orderNumber?: number,
    public description?: string,
    public dictionaryTranslations?: IDictionaryTranslation[],
    public parents?: IDictionaryItem[],
    public children?: IDictionaryItem[]
  ) {}
}
