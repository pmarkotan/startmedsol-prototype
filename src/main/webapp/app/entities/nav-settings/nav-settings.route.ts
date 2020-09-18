import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INavSettings, NavSettings } from 'app/shared/model/nav-settings.model';
import { NavSettingsService } from './nav-settings.service';
import { NavSettingsComponent } from './nav-settings.component';
import { NavSettingsDetailComponent } from './nav-settings-detail.component';
import { NavSettingsUpdateComponent } from './nav-settings-update.component';

@Injectable({ providedIn: 'root' })
export class NavSettingsResolve implements Resolve<INavSettings> {
  constructor(private service: NavSettingsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INavSettings> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((navSettings: HttpResponse<NavSettings>) => {
          if (navSettings.body) {
            return of(navSettings.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NavSettings());
  }
}

export const navSettingsRoute: Routes = [
  {
    path: '',
    component: NavSettingsComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.navSettings.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NavSettingsDetailComponent,
    resolve: {
      navSettings: NavSettingsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.navSettings.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NavSettingsUpdateComponent,
    resolve: {
      navSettings: NavSettingsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.navSettings.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NavSettingsUpdateComponent,
    resolve: {
      navSettings: NavSettingsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.navSettings.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
