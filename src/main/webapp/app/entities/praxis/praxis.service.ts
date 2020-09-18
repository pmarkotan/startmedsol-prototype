import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPraxis } from 'app/shared/model/praxis.model';

type EntityResponseType = HttpResponse<IPraxis>;
type EntityArrayResponseType = HttpResponse<IPraxis[]>;

@Injectable({ providedIn: 'root' })
export class PraxisService {
  public resourceUrl = SERVER_API_URL + 'api/praxes';

  constructor(protected http: HttpClient) {}

  create(praxis: IPraxis): Observable<EntityResponseType> {
    return this.http.post<IPraxis>(this.resourceUrl, praxis, { observe: 'response' });
  }

  update(praxis: IPraxis): Observable<EntityResponseType> {
    return this.http.put<IPraxis>(this.resourceUrl, praxis, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPraxis>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPraxis[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
