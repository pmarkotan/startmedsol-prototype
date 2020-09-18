import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICsProcedure } from 'app/shared/model/cs-procedure.model';

type EntityResponseType = HttpResponse<ICsProcedure>;
type EntityArrayResponseType = HttpResponse<ICsProcedure[]>;

@Injectable({ providedIn: 'root' })
export class CsProcedureService {
  public resourceUrl = SERVER_API_URL + 'api/cs-procedures';

  constructor(protected http: HttpClient) {}

  create(csProcedure: ICsProcedure): Observable<EntityResponseType> {
    return this.http.post<ICsProcedure>(this.resourceUrl, csProcedure, { observe: 'response' });
  }

  update(csProcedure: ICsProcedure): Observable<EntityResponseType> {
    return this.http.put<ICsProcedure>(this.resourceUrl, csProcedure, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICsProcedure>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICsProcedure[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
