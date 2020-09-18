import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPuphaLoader, PuphaLoader } from 'app/shared/model/pupha-loader.model';
import { PuphaLoaderService } from './pupha-loader.service';
import { PuphaLoaderComponent } from './pupha-loader.component';
import { PuphaLoaderDetailComponent } from './pupha-loader-detail.component';
import { PuphaLoaderUpdateComponent } from './pupha-loader-update.component';

@Injectable({ providedIn: 'root' })
export class PuphaLoaderResolve implements Resolve<IPuphaLoader> {
  constructor(private service: PuphaLoaderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPuphaLoader> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((puphaLoader: HttpResponse<PuphaLoader>) => {
          if (puphaLoader.body) {
            return of(puphaLoader.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PuphaLoader());
  }
}

export const puphaLoaderRoute: Routes = [
  {
    path: '',
    component: PuphaLoaderComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.puphaLoader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PuphaLoaderDetailComponent,
    resolve: {
      puphaLoader: PuphaLoaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.puphaLoader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PuphaLoaderUpdateComponent,
    resolve: {
      puphaLoader: PuphaLoaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.puphaLoader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PuphaLoaderUpdateComponent,
    resolve: {
      puphaLoader: PuphaLoaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.puphaLoader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
