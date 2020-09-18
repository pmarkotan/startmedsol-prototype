import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICodeSetLoad } from 'app/shared/model/code-set-load.model';

type EntityResponseType = HttpResponse<ICodeSetLoad>;
type EntityArrayResponseType = HttpResponse<ICodeSetLoad[]>;

@Injectable({ providedIn: 'root' })
export class CodeSetLoadService {
  public resourceUrl = SERVER_API_URL + 'api/code-set-loads';

  constructor(protected http: HttpClient) {}

  create(codeSetLoad: ICodeSetLoad): Observable<EntityResponseType> {
    return this.http.post<ICodeSetLoad>(this.resourceUrl, codeSetLoad, { observe: 'response' });
  }

  update(codeSetLoad: ICodeSetLoad): Observable<EntityResponseType> {
    return this.http.put<ICodeSetLoad>(this.resourceUrl, codeSetLoad, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICodeSetLoad>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICodeSetLoad[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
