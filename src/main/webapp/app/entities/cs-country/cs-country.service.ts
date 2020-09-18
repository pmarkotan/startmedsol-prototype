import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICsCountry } from 'app/shared/model/cs-country.model';

type EntityResponseType = HttpResponse<ICsCountry>;
type EntityArrayResponseType = HttpResponse<ICsCountry[]>;

@Injectable({ providedIn: 'root' })
export class CsCountryService {
  public resourceUrl = SERVER_API_URL + 'api/cs-countries';

  constructor(protected http: HttpClient) {}

  create(csCountry: ICsCountry): Observable<EntityResponseType> {
    return this.http.post<ICsCountry>(this.resourceUrl, csCountry, { observe: 'response' });
  }

  update(csCountry: ICsCountry): Observable<EntityResponseType> {
    return this.http.put<ICsCountry>(this.resourceUrl, csCountry, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICsCountry>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICsCountry[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
