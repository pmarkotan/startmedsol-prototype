import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMedicalCaseDiagnosis, MedicalCaseDiagnosis } from 'app/shared/model/medical-case-diagnosis.model';
import { MedicalCaseDiagnosisService } from './medical-case-diagnosis.service';
import { MedicalCaseDiagnosisComponent } from './medical-case-diagnosis.component';
import { MedicalCaseDiagnosisDetailComponent } from './medical-case-diagnosis-detail.component';
import { MedicalCaseDiagnosisUpdateComponent } from './medical-case-diagnosis-update.component';

@Injectable({ providedIn: 'root' })
export class MedicalCaseDiagnosisResolve implements Resolve<IMedicalCaseDiagnosis> {
  constructor(private service: MedicalCaseDiagnosisService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMedicalCaseDiagnosis> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((medicalCaseDiagnosis: HttpResponse<MedicalCaseDiagnosis>) => {
          if (medicalCaseDiagnosis.body) {
            return of(medicalCaseDiagnosis.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MedicalCaseDiagnosis());
  }
}

export const medicalCaseDiagnosisRoute: Routes = [
  {
    path: '',
    component: MedicalCaseDiagnosisComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.medicalCaseDiagnosis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MedicalCaseDiagnosisDetailComponent,
    resolve: {
      medicalCaseDiagnosis: MedicalCaseDiagnosisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.medicalCaseDiagnosis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MedicalCaseDiagnosisUpdateComponent,
    resolve: {
      medicalCaseDiagnosis: MedicalCaseDiagnosisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.medicalCaseDiagnosis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MedicalCaseDiagnosisUpdateComponent,
    resolve: {
      medicalCaseDiagnosis: MedicalCaseDiagnosisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.medicalCaseDiagnosis.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
