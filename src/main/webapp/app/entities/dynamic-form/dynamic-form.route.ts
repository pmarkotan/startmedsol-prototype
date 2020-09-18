import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDynamicForm, DynamicForm } from 'app/shared/model/dynamic-form.model';
import { DynamicFormService } from './dynamic-form.service';
import { DynamicFormComponent } from './dynamic-form.component';
import { DynamicFormDetailComponent } from './dynamic-form-detail.component';
import { DynamicFormUpdateComponent } from './dynamic-form-update.component';

@Injectable({ providedIn: 'root' })
export class DynamicFormResolve implements Resolve<IDynamicForm> {
  constructor(private service: DynamicFormService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDynamicForm> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dynamicForm: HttpResponse<DynamicForm>) => {
          if (dynamicForm.body) {
            return of(dynamicForm.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DynamicForm());
  }
}

export const dynamicFormRoute: Routes = [
  {
    path: '',
    component: DynamicFormComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.dynamicForm.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DynamicFormDetailComponent,
    resolve: {
      dynamicForm: DynamicFormResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.dynamicForm.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DynamicFormUpdateComponent,
    resolve: {
      dynamicForm: DynamicFormResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.dynamicForm.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DynamicFormUpdateComponent,
    resolve: {
      dynamicForm: DynamicFormResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.dynamicForm.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
