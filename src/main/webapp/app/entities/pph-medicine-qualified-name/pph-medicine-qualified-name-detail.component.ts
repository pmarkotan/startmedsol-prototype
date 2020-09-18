import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPphMedicineQualifiedName } from 'app/shared/model/pph-medicine-qualified-name.model';

@Component({
  selector: 'jhi-pph-medicine-qualified-name-detail',
  templateUrl: './pph-medicine-qualified-name-detail.component.html',
})
export class PphMedicineQualifiedNameDetailComponent implements OnInit {
  pphMedicineQualifiedName: IPphMedicineQualifiedName | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pphMedicineQualifiedName }) => (this.pphMedicineQualifiedName = pphMedicineQualifiedName));
  }

  previousState(): void {
    window.history.back();
  }
}
