import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IContactPerson } from 'app/shared/model/contact-person.model';

type EntityResponseType = HttpResponse<IContactPerson>;
type EntityArrayResponseType = HttpResponse<IContactPerson[]>;

@Injectable({ providedIn: 'root' })
export class ContactPersonService {
  public resourceUrl = SERVER_API_URL + 'api/contact-people';

  constructor(protected http: HttpClient) {}

  create(contactPerson: IContactPerson): Observable<EntityResponseType> {
    return this.http.post<IContactPerson>(this.resourceUrl, contactPerson, { observe: 'response' });
  }

  update(contactPerson: IContactPerson): Observable<EntityResponseType> {
    return this.http.put<IContactPerson>(this.resourceUrl, contactPerson, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IContactPerson>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IContactPerson[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
