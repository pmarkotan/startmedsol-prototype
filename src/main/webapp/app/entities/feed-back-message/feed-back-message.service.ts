import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFeedBackMessage } from 'app/shared/model/feed-back-message.model';

type EntityResponseType = HttpResponse<IFeedBackMessage>;
type EntityArrayResponseType = HttpResponse<IFeedBackMessage[]>;

@Injectable({ providedIn: 'root' })
export class FeedBackMessageService {
  public resourceUrl = SERVER_API_URL + 'api/feed-back-messages';

  constructor(protected http: HttpClient) {}

  create(feedBackMessage: IFeedBackMessage): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(feedBackMessage);
    return this.http
      .post<IFeedBackMessage>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(feedBackMessage: IFeedBackMessage): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(feedBackMessage);
    return this.http
      .put<IFeedBackMessage>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFeedBackMessage>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFeedBackMessage[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(feedBackMessage: IFeedBackMessage): IFeedBackMessage {
    const copy: IFeedBackMessage = Object.assign({}, feedBackMessage, {
      createDate: feedBackMessage.createDate && feedBackMessage.createDate.isValid() ? feedBackMessage.createDate.toJSON() : undefined,
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
      res.body.forEach((feedBackMessage: IFeedBackMessage) => {
        feedBackMessage.createDate = feedBackMessage.createDate ? moment(feedBackMessage.createDate) : undefined;
      });
    }
    return res;
  }
}
