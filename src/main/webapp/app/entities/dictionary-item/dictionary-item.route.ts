import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDictionaryItem, DictionaryItem } from 'app/shared/model/dictionary-item.model';
import { DictionaryItemService } from './dictionary-item.service';
import { DictionaryItemComponent } from './dictionary-item.component';
import { DictionaryItemDetailComponent } from './dictionary-item-detail.component';
import { DictionaryItemUpdateComponent } from './dictionary-item-update.component';

@Injectable({ providedIn: 'root' })
export class DictionaryItemResolve implements Resolve<IDictionaryItem> {
  constructor(private service: DictionaryItemService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDictionaryItem> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dictionaryItem: HttpResponse<DictionaryItem>) => {
          if (dictionaryItem.body) {
            return of(dictionaryItem.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DictionaryItem());
  }
}

export const dictionaryItemRoute: Routes = [
  {
    path: '',
    component: DictionaryItemComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.dictionaryItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DictionaryItemDetailComponent,
    resolve: {
      dictionaryItem: DictionaryItemResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.dictionaryItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DictionaryItemUpdateComponent,
    resolve: {
      dictionaryItem: DictionaryItemResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.dictionaryItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DictionaryItemUpdateComponent,
    resolve: {
      dictionaryItem: DictionaryItemResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.dictionaryItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
