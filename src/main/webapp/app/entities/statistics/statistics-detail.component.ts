import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStatistics } from 'app/shared/model/statistics.model';

@Component({
  selector: 'jhi-statistics-detail',
  templateUrl: './statistics-detail.component.html',
})
export class StatisticsDetailComponent implements OnInit {
  statistics: IStatistics | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ statistics }) => (this.statistics = statistics));
  }

  previousState(): void {
    window.history.back();
  }
}
