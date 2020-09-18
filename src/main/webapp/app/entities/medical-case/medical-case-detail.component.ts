import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedicalCase } from 'app/shared/model/medical-case.model';

@Component({
  selector: 'jhi-medical-case-detail',
  templateUrl: './medical-case-detail.component.html',
})
export class MedicalCaseDetailComponent implements OnInit {
  medicalCase: IMedicalCase | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalCase }) => (this.medicalCase = medicalCase));
  }

  previousState(): void {
    window.history.back();
  }
}
