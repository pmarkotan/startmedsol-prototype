import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPrescriptionEesztId, PrescriptionEesztId } from 'app/shared/model/prescription-eeszt-id.model';
import { PrescriptionEesztIdService } from './prescription-eeszt-id.service';
import { PrescriptionEesztIdComponent } from './prescription-eeszt-id.component';
import { PrescriptionEesztIdDetailComponent } from './prescription-eeszt-id-detail.component';
import { PrescriptionEesztIdUpdateComponent } from './prescription-eeszt-id-update.component';

@Injectable({ providedIn: 'root' })
export class PrescriptionEesztIdResolve implements Resolve<IPrescriptionEesztId> {
  constructor(private service: PrescriptionEesztIdService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPrescriptionEesztId> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((prescriptionEesztId: HttpResponse<PrescriptionEesztId>) => {
          if (prescriptionEesztId.body) {
            return of(prescriptionEesztId.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PrescriptionEesztId());
  }
}

export const prescriptionEesztIdRoute: Routes = [
  {
    path: '',
    component: PrescriptionEesztIdComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.prescriptionEesztId.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PrescriptionEesztIdDetailComponent,
    resolve: {
      prescriptionEesztId: PrescriptionEesztIdResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.prescriptionEesztId.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PrescriptionEesztIdUpdateComponent,
    resolve: {
      prescriptionEesztId: PrescriptionEesztIdResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.prescriptionEesztId.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PrescriptionEesztIdUpdateComponent,
    resolve: {
      prescriptionEesztId: PrescriptionEesztIdResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.prescriptionEesztId.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
