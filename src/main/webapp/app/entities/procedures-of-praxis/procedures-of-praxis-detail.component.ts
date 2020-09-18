import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProceduresOfPraxis } from 'app/shared/model/procedures-of-praxis.model';

@Component({
  selector: 'jhi-procedures-of-praxis-detail',
  templateUrl: './procedures-of-praxis-detail.component.html',
})
export class ProceduresOfPraxisDetailComponent implements OnInit {
  proceduresOfPraxis: IProceduresOfPraxis | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ proceduresOfPraxis }) => (this.proceduresOfPraxis = proceduresOfPraxis));
  }

  previousState(): void {
    window.history.back();
  }
}
