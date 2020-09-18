import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IExternalDocument } from 'app/shared/model/external-document.model';

type EntityResponseType = HttpResponse<IExternalDocument>;
type EntityArrayResponseType = HttpResponse<IExternalDocument[]>;

@Injectable({ providedIn: 'root' })
export class ExternalDocumentService {
  public resourceUrl = SERVER_API_URL + 'api/external-documents';

  constructor(protected http: HttpClient) {}

  create(externalDocument: IExternalDocument): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(externalDocument);
    return this.http
      .post<IExternalDocument>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(externalDocument: IExternalDocument): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(externalDocument);
    return this.http
      .put<IExternalDocument>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IExternalDocument>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IExternalDocument[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(externalDocument: IExternalDocument): IExternalDocument {
    const copy: IExternalDocument = Object.assign({}, externalDocument, {
      created: externalDocument.created && externalDocument.created.isValid() ? externalDocument.created.toJSON() : undefined,
      uploaded: externalDocument.uploaded && externalDocument.uploaded.isValid() ? externalDocument.uploaded.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.created = res.body.created ? moment(res.body.created) : undefined;
      res.body.uploaded = res.body.uploaded ? moment(res.body.uploaded) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((externalDocument: IExternalDocument) => {
        externalDocument.created = externalDocument.created ? moment(externalDocument.created) : undefined;
        externalDocument.uploaded = externalDocument.uploaded ? moment(externalDocument.uploaded) : undefined;
      });
    }
    return res;
  }
}
