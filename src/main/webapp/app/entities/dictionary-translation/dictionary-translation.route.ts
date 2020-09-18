import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDictionaryTranslation, DictionaryTranslation } from 'app/shared/model/dictionary-translation.model';
import { DictionaryTranslationService } from './dictionary-translation.service';
import { DictionaryTranslationComponent } from './dictionary-translation.component';
import { DictionaryTranslationDetailComponent } from './dictionary-translation-detail.component';
import { DictionaryTranslationUpdateComponent } from './dictionary-translation-update.component';

@Injectable({ providedIn: 'root' })
export class DictionaryTranslationResolve implements Resolve<IDictionaryTranslation> {
  constructor(private service: DictionaryTranslationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDictionaryTranslation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dictionaryTranslation: HttpResponse<DictionaryTranslation>) => {
          if (dictionaryTranslation.body) {
            return of(dictionaryTranslation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DictionaryTranslation());
  }
}

export const dictionaryTranslationRoute: Routes = [
  {
    path: '',
    component: DictionaryTranslationComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.dictionaryTranslation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DictionaryTranslationDetailComponent,
    resolve: {
      dictionaryTranslation: DictionaryTranslationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.dictionaryTranslation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DictionaryTranslationUpdateComponent,
    resolve: {
      dictionaryTranslation: DictionaryTranslationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.dictionaryTranslation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DictionaryTranslationUpdateComponent,
    resolve: {
      dictionaryTranslation: DictionaryTranslationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.dictionaryTranslation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
