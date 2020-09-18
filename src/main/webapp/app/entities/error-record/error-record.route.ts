import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IErrorRecord, ErrorRecord } from 'app/shared/model/error-record.model';
import { ErrorRecordService } from './error-record.service';
import { ErrorRecordComponent } from './error-record.component';
import { ErrorRecordDetailComponent } from './error-record-detail.component';
import { ErrorRecordUpdateComponent } from './error-record-update.component';

@Injectable({ providedIn: 'root' })
export class ErrorRecordResolve implements Resolve<IErrorRecord> {
  constructor(private service: ErrorRecordService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IErrorRecord> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((errorRecord: HttpResponse<ErrorRecord>) => {
          if (errorRecord.body) {
            return of(errorRecord.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ErrorRecord());
  }
}

export const errorRecordRoute: Routes = [
  {
    path: '',
    component: ErrorRecordComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.errorRecord.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ErrorRecordDetailComponent,
    resolve: {
      errorRecord: ErrorRecordResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.errorRecord.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ErrorRecordUpdateComponent,
    resolve: {
      errorRecord: ErrorRecordResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.errorRecord.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ErrorRecordUpdateComponent,
    resolve: {
      errorRecord: ErrorRecordResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.errorRecord.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
