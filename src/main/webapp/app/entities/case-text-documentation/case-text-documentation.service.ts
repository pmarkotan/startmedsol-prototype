import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICaseTextDocumentation } from 'app/shared/model/case-text-documentation.model';

type EntityResponseType = HttpResponse<ICaseTextDocumentation>;
type EntityArrayResponseType = HttpResponse<ICaseTextDocumentation[]>;

@Injectable({ providedIn: 'root' })
export class CaseTextDocumentationService {
  public resourceUrl = SERVER_API_URL + 'api/case-text-documentations';

  constructor(protected http: HttpClient) {}

  create(caseTextDocumentation: ICaseTextDocumentation): Observable<EntityResponseType> {
    return this.http.post<ICaseTextDocumentation>(this.resourceUrl, caseTextDocumentation, { observe: 'response' });
  }

  update(caseTextDocumentation: ICaseTextDocumentation): Observable<EntityResponseType> {
    return this.http.put<ICaseTextDocumentation>(this.resourceUrl, caseTextDocumentation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICaseTextDocumentation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICaseTextDocumentation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
