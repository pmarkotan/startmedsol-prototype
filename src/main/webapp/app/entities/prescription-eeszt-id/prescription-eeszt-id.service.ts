import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPrescriptionEesztId } from 'app/shared/model/prescription-eeszt-id.model';

type EntityResponseType = HttpResponse<IPrescriptionEesztId>;
type EntityArrayResponseType = HttpResponse<IPrescriptionEesztId[]>;

@Injectable({ providedIn: 'root' })
export class PrescriptionEesztIdService {
  public resourceUrl = SERVER_API_URL + 'api/prescription-eeszt-ids';

  constructor(protected http: HttpClient) {}

  create(prescriptionEesztId: IPrescriptionEesztId): Observable<EntityResponseType> {
    return this.http.post<IPrescriptionEesztId>(this.resourceUrl, prescriptionEesztId, { observe: 'response' });
  }

  update(prescriptionEesztId: IPrescriptionEesztId): Observable<EntityResponseType> {
    return this.http.put<IPrescriptionEesztId>(this.resourceUrl, prescriptionEesztId, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPrescriptionEesztId>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPrescriptionEesztId[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
