import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEhrDocument, EhrDocument } from 'app/shared/model/ehr-document.model';
import { EhrDocumentService } from './ehr-document.service';
import { EhrDocumentComponent } from './ehr-document.component';
import { EhrDocumentDetailComponent } from './ehr-document-detail.component';
import { EhrDocumentUpdateComponent } from './ehr-document-update.component';

@Injectable({ providedIn: 'root' })
export class EhrDocumentResolve implements Resolve<IEhrDocument> {
  constructor(private service: EhrDocumentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEhrDocument> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ehrDocument: HttpResponse<EhrDocument>) => {
          if (ehrDocument.body) {
            return of(ehrDocument.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EhrDocument());
  }
}

export const ehrDocumentRoute: Routes = [
  {
    path: '',
    component: EhrDocumentComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.ehrDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EhrDocumentDetailComponent,
    resolve: {
      ehrDocument: EhrDocumentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.ehrDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EhrDocumentUpdateComponent,
    resolve: {
      ehrDocument: EhrDocumentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.ehrDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EhrDocumentUpdateComponent,
    resolve: {
      ehrDocument: EhrDocumentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.ehrDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
