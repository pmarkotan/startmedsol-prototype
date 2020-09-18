import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPphMedicineQualifiedName } from 'app/shared/model/pph-medicine-qualified-name.model';

type EntityResponseType = HttpResponse<IPphMedicineQualifiedName>;
type EntityArrayResponseType = HttpResponse<IPphMedicineQualifiedName[]>;

@Injectable({ providedIn: 'root' })
export class PphMedicineQualifiedNameService {
  public resourceUrl = SERVER_API_URL + 'api/pph-medicine-qualified-names';

  constructor(protected http: HttpClient) {}

  create(pphMedicineQualifiedName: IPphMedicineQualifiedName): Observable<EntityResponseType> {
    return this.http.post<IPphMedicineQualifiedName>(this.resourceUrl, pphMedicineQualifiedName, { observe: 'response' });
  }

  update(pphMedicineQualifiedName: IPphMedicineQualifiedName): Observable<EntityResponseType> {
    return this.http.put<IPphMedicineQualifiedName>(this.resourceUrl, pphMedicineQualifiedName, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPphMedicineQualifiedName>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPphMedicineQualifiedName[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
