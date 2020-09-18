import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEhrDocument } from 'app/shared/model/ehr-document.model';

@Component({
  selector: 'jhi-ehr-document-detail',
  templateUrl: './ehr-document-detail.component.html',
})
export class EhrDocumentDetailComponent implements OnInit {
  ehrDocument: IEhrDocument | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ehrDocument }) => (this.ehrDocument = ehrDocument));
  }

  previousState(): void {
    window.history.back();
  }
}
