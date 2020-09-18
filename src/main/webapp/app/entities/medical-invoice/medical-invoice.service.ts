import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMedicalInvoice } from 'app/shared/model/medical-invoice.model';

type EntityResponseType = HttpResponse<IMedicalInvoice>;
type EntityArrayResponseType = HttpResponse<IMedicalInvoice[]>;

@Injectable({ providedIn: 'root' })
export class MedicalInvoiceService {
  public resourceUrl = SERVER_API_URL + 'api/medical-invoices';

  constructor(protected http: HttpClient) {}

  create(medicalInvoice: IMedicalInvoice): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(medicalInvoice);
    return this.http
      .post<IMedicalInvoice>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(medicalInvoice: IMedicalInvoice): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(medicalInvoice);
    return this.http
      .put<IMedicalInvoice>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMedicalInvoice>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMedicalInvoice[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(medicalInvoice: IMedicalInvoice): IMedicalInvoice {
    const copy: IMedicalInvoice = Object.assign({}, medicalInvoice, {
      createdAt: medicalInvoice.createdAt && medicalInvoice.createdAt.isValid() ? medicalInvoice.createdAt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdAt = res.body.createdAt ? moment(res.body.createdAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((medicalInvoice: IMedicalInvoice) => {
        medicalInvoice.createdAt = medicalInvoice.createdAt ? moment(medicalInvoice.createdAt) : undefined;
      });
    }
    return res;
  }
}
