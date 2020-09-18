import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISpecialistsAdvice } from 'app/shared/model/specialists-advice.model';

type EntityResponseType = HttpResponse<ISpecialistsAdvice>;
type EntityArrayResponseType = HttpResponse<ISpecialistsAdvice[]>;

@Injectable({ providedIn: 'root' })
export class SpecialistsAdviceService {
  public resourceUrl = SERVER_API_URL + 'api/specialists-advices';

  constructor(protected http: HttpClient) {}

  create(specialistsAdvice: ISpecialistsAdvice): Observable<EntityResponseType> {
    return this.http.post<ISpecialistsAdvice>(this.resourceUrl, specialistsAdvice, { observe: 'response' });
  }

  update(specialistsAdvice: ISpecialistsAdvice): Observable<EntityResponseType> {
    return this.http.put<ISpecialistsAdvice>(this.resourceUrl, specialistsAdvice, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISpecialistsAdvice>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISpecialistsAdvice[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
