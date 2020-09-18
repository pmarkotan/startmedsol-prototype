import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProceduresOfPraxis } from 'app/shared/model/procedures-of-praxis.model';

type EntityResponseType = HttpResponse<IProceduresOfPraxis>;
type EntityArrayResponseType = HttpResponse<IProceduresOfPraxis[]>;

@Injectable({ providedIn: 'root' })
export class ProceduresOfPraxisService {
  public resourceUrl = SERVER_API_URL + 'api/procedures-of-praxes';

  constructor(protected http: HttpClient) {}

  create(proceduresOfPraxis: IProceduresOfPraxis): Observable<EntityResponseType> {
    return this.http.post<IProceduresOfPraxis>(this.resourceUrl, proceduresOfPraxis, { observe: 'response' });
  }

  update(proceduresOfPraxis: IProceduresOfPraxis): Observable<EntityResponseType> {
    return this.http.put<IProceduresOfPraxis>(this.resourceUrl, proceduresOfPraxis, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProceduresOfPraxis>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProceduresOfPraxis[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
