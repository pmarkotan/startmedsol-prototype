import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICsDiagnosis } from 'app/shared/model/cs-diagnosis.model';

@Component({
  selector: 'jhi-cs-diagnosis-detail',
  templateUrl: './cs-diagnosis-detail.component.html',
})
export class CsDiagnosisDetailComponent implements OnInit {
  csDiagnosis: ICsDiagnosis | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ csDiagnosis }) => (this.csDiagnosis = csDiagnosis));
  }

  previousState(): void {
    window.history.back();
  }
}
