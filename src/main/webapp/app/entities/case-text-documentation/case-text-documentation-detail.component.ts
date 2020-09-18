import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ICaseTextDocumentation } from 'app/shared/model/case-text-documentation.model';

@Component({
  selector: 'jhi-case-text-documentation-detail',
  templateUrl: './case-text-documentation-detail.component.html',
})
export class CaseTextDocumentationDetailComponent implements OnInit {
  caseTextDocumentation: ICaseTextDocumentation | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ caseTextDocumentation }) => (this.caseTextDocumentation = caseTextDocumentation));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
