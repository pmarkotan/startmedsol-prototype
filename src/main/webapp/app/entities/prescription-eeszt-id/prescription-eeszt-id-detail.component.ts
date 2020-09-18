import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPrescriptionEesztId } from 'app/shared/model/prescription-eeszt-id.model';

@Component({
  selector: 'jhi-prescription-eeszt-id-detail',
  templateUrl: './prescription-eeszt-id-detail.component.html',
})
export class PrescriptionEesztIdDetailComponent implements OnInit {
  prescriptionEesztId: IPrescriptionEesztId | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ prescriptionEesztId }) => (this.prescriptionEesztId = prescriptionEesztId));
  }

  previousState(): void {
    window.history.back();
  }
}
