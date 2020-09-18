import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPphMedicine, PphMedicine } from 'app/shared/model/pph-medicine.model';
import { PphMedicineService } from './pph-medicine.service';
import { PphMedicineComponent } from './pph-medicine.component';
import { PphMedicineDetailComponent } from './pph-medicine-detail.component';
import { PphMedicineUpdateComponent } from './pph-medicine-update.component';

@Injectable({ providedIn: 'root' })
export class PphMedicineResolve implements Resolve<IPphMedicine> {
  constructor(private service: PphMedicineService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPphMedicine> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((pphMedicine: HttpResponse<PphMedicine>) => {
          if (pphMedicine.body) {
            return of(pphMedicine.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PphMedicine());
  }
}

export const pphMedicineRoute: Routes = [
  {
    path: '',
    component: PphMedicineComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.pphMedicine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PphMedicineDetailComponent,
    resolve: {
      pphMedicine: PphMedicineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.pphMedicine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PphMedicineUpdateComponent,
    resolve: {
      pphMedicine: PphMedicineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.pphMedicine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PphMedicineUpdateComponent,
    resolve: {
      pphMedicine: PphMedicineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.pphMedicine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
