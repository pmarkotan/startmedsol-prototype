import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPerformedProcedure } from 'app/shared/model/performed-procedure.model';

type EntityResponseType = HttpResponse<IPerformedProcedure>;
type EntityArrayResponseType = HttpResponse<IPerformedProcedure[]>;

@Injectable({ providedIn: 'root' })
export class PerformedProcedureService {
  public resourceUrl = SERVER_API_URL + 'api/performed-procedures';

  constructor(protected http: HttpClient) {}

  create(performedProcedure: IPerformedProcedure): Observable<EntityResponseType> {
    return this.http.post<IPerformedProcedure>(this.resourceUrl, performedProcedure, { observe: 'response' });
  }

  update(performedProcedure: IPerformedProcedure): Observable<EntityResponseType> {
    return this.http.put<IPerformedProcedure>(this.resourceUrl, performedProcedure, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPerformedProcedure>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPerformedProcedure[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
