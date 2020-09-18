import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICodeSetLoad, CodeSetLoad } from 'app/shared/model/code-set-load.model';
import { CodeSetLoadService } from './code-set-load.service';
import { CodeSetLoadComponent } from './code-set-load.component';
import { CodeSetLoadDetailComponent } from './code-set-load-detail.component';
import { CodeSetLoadUpdateComponent } from './code-set-load-update.component';

@Injectable({ providedIn: 'root' })
export class CodeSetLoadResolve implements Resolve<ICodeSetLoad> {
  constructor(private service: CodeSetLoadService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICodeSetLoad> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((codeSetLoad: HttpResponse<CodeSetLoad>) => {
          if (codeSetLoad.body) {
            return of(codeSetLoad.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CodeSetLoad());
  }
}

export const codeSetLoadRoute: Routes = [
  {
    path: '',
    component: CodeSetLoadComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.codeSetLoad.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CodeSetLoadDetailComponent,
    resolve: {
      codeSetLoad: CodeSetLoadResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.codeSetLoad.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CodeSetLoadUpdateComponent,
    resolve: {
      codeSetLoad: CodeSetLoadResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.codeSetLoad.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CodeSetLoadUpdateComponent,
    resolve: {
      codeSetLoad: CodeSetLoadResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.codeSetLoad.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
