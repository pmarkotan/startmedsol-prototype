import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPraxis } from 'app/shared/model/praxis.model';

@Component({
  selector: 'jhi-praxis-detail',
  templateUrl: './praxis-detail.component.html',
})
export class PraxisDetailComponent implements OnInit {
  praxis: IPraxis | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ praxis }) => (this.praxis = praxis));
  }

  previousState(): void {
    window.history.back();
  }
}
