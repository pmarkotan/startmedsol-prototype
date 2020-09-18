import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDataGenerator, DataGenerator } from 'app/shared/model/data-generator.model';
import { DataGeneratorService } from './data-generator.service';
import { DataGeneratorComponent } from './data-generator.component';
import { DataGeneratorDetailComponent } from './data-generator-detail.component';
import { DataGeneratorUpdateComponent } from './data-generator-update.component';

@Injectable({ providedIn: 'root' })
export class DataGeneratorResolve implements Resolve<IDataGenerator> {
  constructor(private service: DataGeneratorService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDataGenerator> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dataGenerator: HttpResponse<DataGenerator>) => {
          if (dataGenerator.body) {
            return of(dataGenerator.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DataGenerator());
  }
}

export const dataGeneratorRoute: Routes = [
  {
    path: '',
    component: DataGeneratorComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.dataGenerator.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DataGeneratorDetailComponent,
    resolve: {
      dataGenerator: DataGeneratorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.dataGenerator.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DataGeneratorUpdateComponent,
    resolve: {
      dataGenerator: DataGeneratorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.dataGenerator.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DataGeneratorUpdateComponent,
    resolve: {
      dataGenerator: DataGeneratorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.dataGenerator.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
