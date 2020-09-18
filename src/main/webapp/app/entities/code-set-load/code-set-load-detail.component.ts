import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICodeSetLoad } from 'app/shared/model/code-set-load.model';

@Component({
  selector: 'jhi-code-set-load-detail',
  templateUrl: './code-set-load-detail.component.html',
})
export class CodeSetLoadDetailComponent implements OnInit {
  codeSetLoad: ICodeSetLoad | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ codeSetLoad }) => (this.codeSetLoad = codeSetLoad));
  }

  previousState(): void {
    window.history.back();
  }
}
