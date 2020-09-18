import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDictionaryTranslation } from 'app/shared/model/dictionary-translation.model';

type EntityResponseType = HttpResponse<IDictionaryTranslation>;
type EntityArrayResponseType = HttpResponse<IDictionaryTranslation[]>;

@Injectable({ providedIn: 'root' })
export class DictionaryTranslationService {
  public resourceUrl = SERVER_API_URL + 'api/dictionary-translations';

  constructor(protected http: HttpClient) {}

  create(dictionaryTranslation: IDictionaryTranslation): Observable<EntityResponseType> {
    return this.http.post<IDictionaryTranslation>(this.resourceUrl, dictionaryTranslation, { observe: 'response' });
  }

  update(dictionaryTranslation: IDictionaryTranslation): Observable<EntityResponseType> {
    return this.http.put<IDictionaryTranslation>(this.resourceUrl, dictionaryTranslation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDictionaryTranslation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDictionaryTranslation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
