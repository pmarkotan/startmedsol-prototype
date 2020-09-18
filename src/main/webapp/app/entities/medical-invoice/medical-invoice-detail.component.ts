import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedicalInvoice } from 'app/shared/model/medical-invoice.model';

@Component({
  selector: 'jhi-medical-invoice-detail',
  templateUrl: './medical-invoice-detail.component.html',
})
export class MedicalInvoiceDetailComponent implements OnInit {
  medicalInvoice: IMedicalInvoice | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalInvoice }) => (this.medicalInvoice = medicalInvoice));
  }

  previousState(): void {
    window.history.back();
  }
}
