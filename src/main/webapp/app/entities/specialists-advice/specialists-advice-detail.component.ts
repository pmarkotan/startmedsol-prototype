import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISpecialistsAdvice } from 'app/shared/model/specialists-advice.model';

@Component({
  selector: 'jhi-specialists-advice-detail',
  templateUrl: './specialists-advice-detail.component.html',
})
export class SpecialistsAdviceDetailComponent implements OnInit {
  specialistsAdvice: ISpecialistsAdvice | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ specialistsAdvice }) => (this.specialistsAdvice = specialistsAdvice));
  }

  previousState(): void {
    window.history.back();
  }
}
