import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IErrorRecord } from 'app/shared/model/error-record.model';

type EntityResponseType = HttpResponse<IErrorRecord>;
type EntityArrayResponseType = HttpResponse<IErrorRecord[]>;

@Injectable({ providedIn: 'root' })
export class ErrorRecordService {
  public resourceUrl = SERVER_API_URL + 'api/error-records';

  constructor(protected http: HttpClient) {}

  create(errorRecord: IErrorRecord): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(errorRecord);
    return this.http
      .post<IErrorRecord>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(errorRecord: IErrorRecord): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(errorRecord);
    return this.http
      .put<IErrorRecord>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IErrorRecord>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IErrorRecord[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(errorRecord: IErrorRecord): IErrorRecord {
    const copy: IErrorRecord = Object.assign({}, errorRecord, {
      createDate: errorRecord.createDate && errorRecord.createDate.isValid() ? errorRecord.createDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createDate = res.body.createDate ? moment(res.body.createDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((errorRecord: IErrorRecord) => {
        errorRecord.createDate = errorRecord.createDate ? moment(errorRecord.createDate) : undefined;
      });
    }
    return res;
  }
}
