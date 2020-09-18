import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDataGenerator } from 'app/shared/model/data-generator.model';

@Component({
  selector: 'jhi-data-generator-detail',
  templateUrl: './data-generator-detail.component.html',
})
export class DataGeneratorDetailComponent implements OnInit {
  dataGenerator: IDataGenerator | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dataGenerator }) => (this.dataGenerator = dataGenerator));
  }

  previousState(): void {
    window.history.back();
  }
}
