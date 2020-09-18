import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICaseTextDocumentation, CaseTextDocumentation } from 'app/shared/model/case-text-documentation.model';
import { CaseTextDocumentationService } from './case-text-documentation.service';
import { CaseTextDocumentationComponent } from './case-text-documentation.component';
import { CaseTextDocumentationDetailComponent } from './case-text-documentation-detail.component';
import { CaseTextDocumentationUpdateComponent } from './case-text-documentation-update.component';

@Injectable({ providedIn: 'root' })
export class CaseTextDocumentationResolve implements Resolve<ICaseTextDocumentation> {
  constructor(private service: CaseTextDocumentationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICaseTextDocumentation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((caseTextDocumentation: HttpResponse<CaseTextDocumentation>) => {
          if (caseTextDocumentation.body) {
            return of(caseTextDocumentation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CaseTextDocumentation());
  }
}

export const caseTextDocumentationRoute: Routes = [
  {
    path: '',
    component: CaseTextDocumentationComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.caseTextDocumentation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CaseTextDocumentationDetailComponent,
    resolve: {
      caseTextDocumentation: CaseTextDocumentationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.caseTextDocumentation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CaseTextDocumentationUpdateComponent,
    resolve: {
      caseTextDocumentation: CaseTextDocumentationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.caseTextDocumentation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CaseTextDocumentationUpdateComponent,
    resolve: {
      caseTextDocumentation: CaseTextDocumentationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.caseTextDocumentation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
