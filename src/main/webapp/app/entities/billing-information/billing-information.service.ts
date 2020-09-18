import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBillingInformation } from 'app/shared/model/billing-information.model';

type EntityResponseType = HttpResponse<IBillingInformation>;
type EntityArrayResponseType = HttpResponse<IBillingInformation[]>;

@Injectable({ providedIn: 'root' })
export class BillingInformationService {
  public resourceUrl = SERVER_API_URL + 'api/billing-informations';

  constructor(protected http: HttpClient) {}

  create(billingInformation: IBillingInformation): Observable<EntityResponseType> {
    return this.http.post<IBillingInformation>(this.resourceUrl, billingInformation, { observe: 'response' });
  }

  update(billingInformation: IBillingInformation): Observable<EntityResponseType> {
    return this.http.put<IBillingInformation>(this.resourceUrl, billingInformation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBillingInformation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBillingInformation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
