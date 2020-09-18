import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedicalCaseDiagnosis } from 'app/shared/model/medical-case-diagnosis.model';

@Component({
  selector: 'jhi-medical-case-diagnosis-detail',
  templateUrl: './medical-case-diagnosis-detail.component.html',
})
export class MedicalCaseDiagnosisDetailComponent implements OnInit {
  medicalCaseDiagnosis: IMedicalCaseDiagnosis | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalCaseDiagnosis }) => (this.medicalCaseDiagnosis = medicalCaseDiagnosis));
  }

  previousState(): void {
    window.history.back();
  }
}
