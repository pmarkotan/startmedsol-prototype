import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IContactPerson, ContactPerson } from 'app/shared/model/contact-person.model';
import { ContactPersonService } from './contact-person.service';
import { ContactPersonComponent } from './contact-person.component';
import { ContactPersonDetailComponent } from './contact-person-detail.component';
import { ContactPersonUpdateComponent } from './contact-person-update.component';

@Injectable({ providedIn: 'root' })
export class ContactPersonResolve implements Resolve<IContactPerson> {
  constructor(private service: ContactPersonService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IContactPerson> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((contactPerson: HttpResponse<ContactPerson>) => {
          if (contactPerson.body) {
            return of(contactPerson.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ContactPerson());
  }
}

export const contactPersonRoute: Routes = [
  {
    path: '',
    component: ContactPersonComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.contactPerson.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ContactPersonDetailComponent,
    resolve: {
      contactPerson: ContactPersonResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.contactPerson.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ContactPersonUpdateComponent,
    resolve: {
      contactPerson: ContactPersonResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.contactPerson.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ContactPersonUpdateComponent,
    resolve: {
      contactPerson: ContactPersonResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.contactPerson.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
