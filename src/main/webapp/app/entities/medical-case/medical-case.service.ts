import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMedicalCase } from 'app/shared/model/medical-case.model';

type EntityResponseType = HttpResponse<IMedicalCase>;
type EntityArrayResponseType = HttpResponse<IMedicalCase[]>;

@Injectable({ providedIn: 'root' })
export class MedicalCaseService {
  public resourceUrl = SERVER_API_URL + 'api/medical-cases';

  constructor(protected http: HttpClient) {}

  create(medicalCase: IMedicalCase): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(medicalCase);
    return this.http
      .post<IMedicalCase>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(medicalCase: IMedicalCase): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(medicalCase);
    return this.http
      .put<IMedicalCase>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMedicalCase>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMedicalCase[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(medicalCase: IMedicalCase): IMedicalCase {
    const copy: IMedicalCase = Object.assign({}, medicalCase, {
      admissionDate: medicalCase.admissionDate && medicalCase.admissionDate.isValid() ? medicalCase.admissionDate.toJSON() : undefined,
      closeDate: medicalCase.closeDate && medicalCase.closeDate.isValid() ? medicalCase.closeDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.admissionDate = res.body.admissionDate ? moment(res.body.admissionDate) : undefined;
      res.body.closeDate = res.body.closeDate ? moment(res.body.closeDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((medicalCase: IMedicalCase) => {
        medicalCase.admissionDate = medicalCase.admissionDate ? moment(medicalCase.admissionDate) : undefined;
        medicalCase.closeDate = medicalCase.closeDate ? moment(medicalCase.closeDate) : undefined;
      });
    }
    return res;
  }
}
