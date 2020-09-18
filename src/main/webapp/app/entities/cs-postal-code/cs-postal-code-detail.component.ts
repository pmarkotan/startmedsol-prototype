import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICsPostalCode } from 'app/shared/model/cs-postal-code.model';

@Component({
  selector: 'jhi-cs-postal-code-detail',
  templateUrl: './cs-postal-code-detail.component.html',
})
export class CsPostalCodeDetailComponent implements OnInit {
  csPostalCode: ICsPostalCode | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ csPostalCode }) => (this.csPostalCode = csPostalCode));
  }

  previousState(): void {
    window.history.back();
  }
}
