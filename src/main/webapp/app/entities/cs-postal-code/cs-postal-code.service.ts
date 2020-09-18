import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICsPostalCode } from 'app/shared/model/cs-postal-code.model';

type EntityResponseType = HttpResponse<ICsPostalCode>;
type EntityArrayResponseType = HttpResponse<ICsPostalCode[]>;

@Injectable({ providedIn: 'root' })
export class CsPostalCodeService {
  public resourceUrl = SERVER_API_URL + 'api/cs-postal-codes';

  constructor(protected http: HttpClient) {}

  create(csPostalCode: ICsPostalCode): Observable<EntityResponseType> {
    return this.http.post<ICsPostalCode>(this.resourceUrl, csPostalCode, { observe: 'response' });
  }

  update(csPostalCode: ICsPostalCode): Observable<EntityResponseType> {
    return this.http.put<ICsPostalCode>(this.resourceUrl, csPostalCode, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICsPostalCode>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICsPostalCode[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
