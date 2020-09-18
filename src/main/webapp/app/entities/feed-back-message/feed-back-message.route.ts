import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFeedBackMessage, FeedBackMessage } from 'app/shared/model/feed-back-message.model';
import { FeedBackMessageService } from './feed-back-message.service';
import { FeedBackMessageComponent } from './feed-back-message.component';
import { FeedBackMessageDetailComponent } from './feed-back-message-detail.component';
import { FeedBackMessageUpdateComponent } from './feed-back-message-update.component';

@Injectable({ providedIn: 'root' })
export class FeedBackMessageResolve implements Resolve<IFeedBackMessage> {
  constructor(private service: FeedBackMessageService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFeedBackMessage> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((feedBackMessage: HttpResponse<FeedBackMessage>) => {
          if (feedBackMessage.body) {
            return of(feedBackMessage.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FeedBackMessage());
  }
}

export const feedBackMessageRoute: Routes = [
  {
    path: '',
    component: FeedBackMessageComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.feedBackMessage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FeedBackMessageDetailComponent,
    resolve: {
      feedBackMessage: FeedBackMessageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.feedBackMessage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FeedBackMessageUpdateComponent,
    resolve: {
      feedBackMessage: FeedBackMessageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.feedBackMessage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FeedBackMessageUpdateComponent,
    resolve: {
      feedBackMessage: FeedBackMessageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.feedBackMessage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
