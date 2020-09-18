import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPerformedProcedure } from 'app/shared/model/performed-procedure.model';

@Component({
  selector: 'jhi-performed-procedure-detail',
  templateUrl: './performed-procedure-detail.component.html',
})
export class PerformedProcedureDetailComponent implements OnInit {
  performedProcedure: IPerformedProcedure | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ performedProcedure }) => (this.performedProcedure = performedProcedure));
  }

  previousState(): void {
    window.history.back();
  }
}
