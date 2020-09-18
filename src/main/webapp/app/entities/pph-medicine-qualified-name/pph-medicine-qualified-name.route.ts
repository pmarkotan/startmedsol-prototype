import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPphMedicineQualifiedName, PphMedicineQualifiedName } from 'app/shared/model/pph-medicine-qualified-name.model';
import { PphMedicineQualifiedNameService } from './pph-medicine-qualified-name.service';
import { PphMedicineQualifiedNameComponent } from './pph-medicine-qualified-name.component';
import { PphMedicineQualifiedNameDetailComponent } from './pph-medicine-qualified-name-detail.component';
import { PphMedicineQualifiedNameUpdateComponent } from './pph-medicine-qualified-name-update.component';

@Injectable({ providedIn: 'root' })
export class PphMedicineQualifiedNameResolve implements Resolve<IPphMedicineQualifiedName> {
  constructor(private service: PphMedicineQualifiedNameService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPphMedicineQualifiedName> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((pphMedicineQualifiedName: HttpResponse<PphMedicineQualifiedName>) => {
          if (pphMedicineQualifiedName.body) {
            return of(pphMedicineQualifiedName.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PphMedicineQualifiedName());
  }
}

export const pphMedicineQualifiedNameRoute: Routes = [
  {
    path: '',
    component: PphMedicineQualifiedNameComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.pphMedicineQualifiedName.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PphMedicineQualifiedNameDetailComponent,
    resolve: {
      pphMedicineQualifiedName: PphMedicineQualifiedNameResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.pphMedicineQualifiedName.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PphMedicineQualifiedNameUpdateComponent,
    resolve: {
      pphMedicineQualifiedName: PphMedicineQualifiedNameResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.pphMedicineQualifiedName.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PphMedicineQualifiedNameUpdateComponent,
    resolve: {
      pphMedicineQualifiedName: PphMedicineQualifiedNameResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.pphMedicineQualifiedName.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
