import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExternalDocument } from 'app/shared/model/external-document.model';

@Component({
  selector: 'jhi-external-document-detail',
  templateUrl: './external-document-detail.component.html',
})
export class ExternalDocumentDetailComponent implements OnInit {
  externalDocument: IExternalDocument | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ externalDocument }) => (this.externalDocument = externalDocument));
  }

  previousState(): void {
    window.history.back();
  }
}
