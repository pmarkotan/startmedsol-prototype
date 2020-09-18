import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPphMedicine } from 'app/shared/model/pph-medicine.model';

@Component({
  selector: 'jhi-pph-medicine-detail',
  templateUrl: './pph-medicine-detail.component.html',
})
export class PphMedicineDetailComponent implements OnInit {
  pphMedicine: IPphMedicine | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pphMedicine }) => (this.pphMedicine = pphMedicine));
  }

  previousState(): void {
    window.history.back();
  }
}
