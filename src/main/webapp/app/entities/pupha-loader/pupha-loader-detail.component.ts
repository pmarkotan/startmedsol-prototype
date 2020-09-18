import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPuphaLoader } from 'app/shared/model/pupha-loader.model';

@Component({
  selector: 'jhi-pupha-loader-detail',
  templateUrl: './pupha-loader-detail.component.html',
})
export class PuphaLoaderDetailComponent implements OnInit {
  puphaLoader: IPuphaLoader | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ puphaLoader }) => (this.puphaLoader = puphaLoader));
  }

  previousState(): void {
    window.history.back();
  }
}
