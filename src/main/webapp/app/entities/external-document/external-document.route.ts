import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IExternalDocument, ExternalDocument } from 'app/shared/model/external-document.model';
import { ExternalDocumentService } from './external-document.service';
import { ExternalDocumentComponent } from './external-document.component';
import { ExternalDocumentDetailComponent } from './external-document-detail.component';
import { ExternalDocumentUpdateComponent } from './external-document-update.component';

@Injectable({ providedIn: 'root' })
export class ExternalDocumentResolve implements Resolve<IExternalDocument> {
  constructor(private service: ExternalDocumentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IExternalDocument> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((externalDocument: HttpResponse<ExternalDocument>) => {
          if (externalDocument.body) {
            return of(externalDocument.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ExternalDocument());
  }
}

export const externalDocumentRoute: Routes = [
  {
    path: '',
    component: ExternalDocumentComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'startMedsolPrototypeApp.externalDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ExternalDocumentDetailComponent,
    resolve: {
      externalDocument: ExternalDocumentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.externalDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ExternalDocumentUpdateComponent,
    resolve: {
      externalDocument: ExternalDocumentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.externalDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ExternalDocumentUpdateComponent,
    resolve: {
      externalDocument: ExternalDocumentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'startMedsolPrototypeApp.externalDocument.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
