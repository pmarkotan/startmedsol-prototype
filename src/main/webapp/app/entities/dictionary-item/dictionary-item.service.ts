import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDictionaryItem } from 'app/shared/model/dictionary-item.model';

type EntityResponseType = HttpResponse<IDictionaryItem>;
type EntityArrayResponseType = HttpResponse<IDictionaryItem[]>;

@Injectable({ providedIn: 'root' })
export class DictionaryItemService {
  public resourceUrl = SERVER_API_URL + 'api/dictionary-items';

  constructor(protected http: HttpClient) {}

  create(dictionaryItem: IDictionaryItem): Observable<EntityResponseType> {
    return this.http.post<IDictionaryItem>(this.resourceUrl, dictionaryItem, { observe: 'response' });
  }

  update(dictionaryItem: IDictionaryItem): Observable<EntityResponseType> {
    return this.http.put<IDictionaryItem>(this.resourceUrl, dictionaryItem, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDictionaryItem>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDictionaryItem[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
