import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPphMedicine } from 'app/shared/model/pph-medicine.model';

type EntityResponseType = HttpResponse<IPphMedicine>;
type EntityArrayResponseType = HttpResponse<IPphMedicine[]>;

@Injectable({ providedIn: 'root' })
export class PphMedicineService {
  public resourceUrl = SERVER_API_URL + 'api/pph-medicines';

  constructor(protected http: HttpClient) {}

  create(pphMedicine: IPphMedicine): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pphMedicine);
    return this.http
      .post<IPphMedicine>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(pphMedicine: IPphMedicine): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pphMedicine);
    return this.http
      .put<IPphMedicine>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPphMedicine>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPphMedicine[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(pphMedicine: IPphMedicine): IPphMedicine {
    const copy: IPphMedicine = Object.assign({}, pphMedicine, {
      mbookDeletedDate:
        pphMedicine.mbookDeletedDate && pphMedicine.mbookDeletedDate.isValid() ? pphMedicine.mbookDeletedDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.mbookDeletedDate = res.body.mbookDeletedDate ? moment(res.body.mbookDeletedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((pphMedicine: IPphMedicine) => {
        pphMedicine.mbookDeletedDate = pphMedicine.mbookDeletedDate ? moment(pphMedicine.mbookDeletedDate) : undefined;
      });
    }
    return res;
  }
}
