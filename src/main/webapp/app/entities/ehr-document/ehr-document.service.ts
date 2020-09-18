import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEhrDocument } from 'app/shared/model/ehr-document.model';

type EntityResponseType = HttpResponse<IEhrDocument>;
type EntityArrayResponseType = HttpResponse<IEhrDocument[]>;

@Injectable({ providedIn: 'root' })
export class EhrDocumentService {
  public resourceUrl = SERVER_API_URL + 'api/ehr-documents';

  constructor(protected http: HttpClient) {}

  create(ehrDocument: IEhrDocument): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ehrDocument);
    return this.http
      .post<IEhrDocument>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(ehrDocument: IEhrDocument): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ehrDocument);
    return this.http
      .put<IEhrDocument>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEhrDocument>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEhrDocument[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(ehrDocument: IEhrDocument): IEhrDocument {
    const copy: IEhrDocument = Object.assign({}, ehrDocument, {
      created: ehrDocument.created && ehrDocument.created.isValid() ? ehrDocument.created.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.created = res.body.created ? moment(res.body.created) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((ehrDocument: IEhrDocument) => {
        ehrDocument.created = ehrDocument.created ? moment(ehrDocument.created) : undefined;
      });
    }
    return res;
  }
}
