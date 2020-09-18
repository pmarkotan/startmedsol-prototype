import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { MedicalInvoiceDetailComponent } from 'app/entities/medical-invoice/medical-invoice-detail.component';
import { MedicalInvoice } from 'app/shared/model/medical-invoice.model';

describe('Component Tests', () => {
  describe('MedicalInvoice Management Detail Component', () => {
    let comp: MedicalInvoiceDetailComponent;
    let fixture: ComponentFixture<MedicalInvoiceDetailComponent>;
    const route = ({ data: of({ medicalInvoice: new MedicalInvoice(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [MedicalInvoiceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MedicalInvoiceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MedicalInvoiceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load medicalInvoice on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.medicalInvoice).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
