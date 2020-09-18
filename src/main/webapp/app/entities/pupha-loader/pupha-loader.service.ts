import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPuphaLoader } from 'app/shared/model/pupha-loader.model';

type EntityResponseType = HttpResponse<IPuphaLoader>;
type EntityArrayResponseType = HttpResponse<IPuphaLoader[]>;

@Injectable({ providedIn: 'root' })
export class PuphaLoaderService {
  public resourceUrl = SERVER_API_URL + 'api/pupha-loaders';

  constructor(protected http: HttpClient) {}

  create(puphaLoader: IPuphaLoader): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(puphaLoader);
    return this.http
      .post<IPuphaLoader>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(puphaLoader: IPuphaLoader): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(puphaLoader);
    return this.http
      .put<IPuphaLoader>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPuphaLoader>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPuphaLoader[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(puphaLoader: IPuphaLoader): IPuphaLoader {
    const copy: IPuphaLoader = Object.assign({}, puphaLoader, {
      time: puphaLoader.time && puphaLoader.time.isValid() ? puphaLoader.time.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.time = res.body.time ? moment(res.body.time) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((puphaLoader: IPuphaLoader) => {
        puphaLoader.time = puphaLoader.time ? moment(puphaLoader.time) : undefined;
      });
    }
    return res;
  }
}
