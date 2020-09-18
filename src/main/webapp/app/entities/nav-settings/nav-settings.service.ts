import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INavSettings } from 'app/shared/model/nav-settings.model';

type EntityResponseType = HttpResponse<INavSettings>;
type EntityArrayResponseType = HttpResponse<INavSettings[]>;

@Injectable({ providedIn: 'root' })
export class NavSettingsService {
  public resourceUrl = SERVER_API_URL + 'api/nav-settings';

  constructor(protected http: HttpClient) {}

  create(navSettings: INavSettings): Observable<EntityResponseType> {
    return this.http.post<INavSettings>(this.resourceUrl, navSettings, { observe: 'response' });
  }

  update(navSettings: INavSettings): Observable<EntityResponseType> {
    return this.http.put<INavSettings>(this.resourceUrl, navSettings, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INavSettings>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INavSettings[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
