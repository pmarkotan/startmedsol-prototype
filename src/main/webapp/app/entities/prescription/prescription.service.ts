import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPrescription } from 'app/shared/model/prescription.model';

type EntityResponseType = HttpResponse<IPrescription>;
type EntityArrayResponseType = HttpResponse<IPrescription[]>;

@Injectable({ providedIn: 'root' })
export class PrescriptionService {
  public resourceUrl = SERVER_API_URL + 'api/prescriptions';

  constructor(protected http: HttpClient) {}

  create(prescription: IPrescription): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(prescription);
    return this.http
      .post<IPrescription>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(prescription: IPrescription): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(prescription);
    return this.http
      .put<IPrescription>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPrescription>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPrescription[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(prescription: IPrescription): IPrescription {
    const copy: IPrescription = Object.assign({}, prescription, {
      inscriptionDate:
        prescription.inscriptionDate && prescription.inscriptionDate.isValid() ? prescription.inscriptionDate.toJSON() : undefined,
      proposalDate: prescription.proposalDate && prescription.proposalDate.isValid() ? prescription.proposalDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.inscriptionDate = res.body.inscriptionDate ? moment(res.body.inscriptionDate) : undefined;
      res.body.proposalDate = res.body.proposalDate ? moment(res.body.proposalDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((prescription: IPrescription) => {
        prescription.inscriptionDate = prescription.inscriptionDate ? moment(prescription.inscriptionDate) : undefined;
        prescription.proposalDate = prescription.proposalDate ? moment(prescription.proposalDate) : undefined;
      });
    }
    return res;
  }
}
