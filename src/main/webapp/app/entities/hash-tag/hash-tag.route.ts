import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IHashTag, HashTag } from 'app/shared/model/hash-tag.model';
import { HashTagService } from './hash-tag.service';
import { HashTagComponent } from './hash-tag.component';
import { HashTagDetailComponent } from './hash-tag-detail.component';
import { HashTagUpdateComponent } from './hash-tag-update.component';

@Injectable({ providedIn: 'root' })
export class HashTagResolve implements Resolve<IHashTag> {
  constructor(private service: HashTagService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IHashTag> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((hashTag: HttpResponse<HashTag>) => {
          if (hashTag.body) {
            return of(hashTag.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new HashTag());
  }
}

export const hashTagRoute: Routes = [
  {
    path: '',
    component: HashTagComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.hashTag.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: HashTagDetailComponent,
    resolve: {
      hashTag: HashTagResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.hashTag.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: HashTagUpdateComponent,
    resolve: {
      hashTag: HashTagResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.hashTag.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: HashTagUpdateComponent,
    resolve: {
      hashTag: HashTagResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.hashTag.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
