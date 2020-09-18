import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IStatistics } from 'app/shared/model/statistics.model';

type EntityResponseType = HttpResponse<IStatistics>;
type EntityArrayResponseType = HttpResponse<IStatistics[]>;

@Injectable({ providedIn: 'root' })
export class StatisticsService {
  public resourceUrl = SERVER_API_URL + 'api/statistics';

  constructor(protected http: HttpClient) {}

  create(statistics: IStatistics): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(statistics);
    return this.http
      .post<IStatistics>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(statistics: IStatistics): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(statistics);
    return this.http
      .put<IStatistics>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IStatistics>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IStatistics[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(statistics: IStatistics): IStatistics {
    const copy: IStatistics = Object.assign({}, statistics, {
      created: statistics.created && statistics.created.isValid() ? statistics.created.toJSON() : undefined,
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
      res.body.forEach((statistics: IStatistics) => {
        statistics.created = statistics.created ? moment(statistics.created) : undefined;
      });
    }
    return res;
  }
}
