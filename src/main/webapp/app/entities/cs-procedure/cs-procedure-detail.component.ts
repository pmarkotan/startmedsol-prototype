import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICsProcedure } from 'app/shared/model/cs-procedure.model';

@Component({
  selector: 'jhi-cs-procedure-detail',
  templateUrl: './cs-procedure-detail.component.html',
})
export class CsProcedureDetailComponent implements OnInit {
  csProcedure: ICsProcedure | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ csProcedure }) => (this.csProcedure = csProcedure));
  }

  previousState(): void {
    window.history.back();
  }
}
