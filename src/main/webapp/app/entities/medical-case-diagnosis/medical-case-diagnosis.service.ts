import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMedicalCaseDiagnosis } from 'app/shared/model/medical-case-diagnosis.model';

type EntityResponseType = HttpResponse<IMedicalCaseDiagnosis>;
type EntityArrayResponseType = HttpResponse<IMedicalCaseDiagnosis[]>;

@Injectable({ providedIn: 'root' })
export class MedicalCaseDiagnosisService {
  public resourceUrl = SERVER_API_URL + 'api/medical-case-diagnoses';

  constructor(protected http: HttpClient) {}

  create(medicalCaseDiagnosis: IMedicalCaseDiagnosis): Observable<EntityResponseType> {
    return this.http.post<IMedicalCaseDiagnosis>(this.resourceUrl, medicalCaseDiagnosis, { observe: 'response' });
  }

  update(medicalCaseDiagnosis: IMedicalCaseDiagnosis): Observable<EntityResponseType> {
    return this.http.put<IMedicalCaseDiagnosis>(this.resourceUrl, medicalCaseDiagnosis, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMedicalCaseDiagnosis>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMedicalCaseDiagnosis[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
