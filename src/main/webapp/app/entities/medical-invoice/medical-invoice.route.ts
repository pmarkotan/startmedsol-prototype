import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMedicalInvoice, MedicalInvoice } from 'app/shared/model/medical-invoice.model';
import { MedicalInvoiceService } from './medical-invoice.service';
import { MedicalInvoiceComponent } from './medical-invoice.component';
import { MedicalInvoiceDetailComponent } from './medical-invoice-detail.component';
import { MedicalInvoiceUpdateComponent } from './medical-invoice-update.component';

@Injectable({ providedIn: 'root' })
export class MedicalInvoiceResolve implements Resolve<IMedicalInvoice> {
  constructor(private service: MedicalInvoiceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMedicalInvoice> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((medicalInvoice: HttpResponse<MedicalInvoice>) => {
          if (medicalInvoice.body) {
            return of(medicalInvoice.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MedicalInvoice());
  }
}

export const medicalInvoiceRoute: Routes = [
  {
    path: '',
    component: MedicalInvoiceComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.medicalInvoice.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MedicalInvoiceDetailComponent,
    resolve: {
      medicalInvoice: MedicalInvoiceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.medicalInvoice.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MedicalInvoiceUpdateComponent,
    resolve: {
      medicalInvoice: MedicalInvoiceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.medicalInvoice.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MedicalInvoiceUpdateComponent,
    resolve: {
      medicalInvoice: MedicalInvoiceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.medicalInvoice.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
