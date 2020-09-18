import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { MedicalInvoiceUpdateComponent } from 'app/entities/medical-invoice/medical-invoice-update.component';
import { MedicalInvoiceService } from 'app/entities/medical-invoice/medical-invoice.service';
import { MedicalInvoice } from 'app/shared/model/medical-invoice.model';

describe('Component Tests', () => {
  describe('MedicalInvoice Management Update Component', () => {
    let comp: MedicalInvoiceUpdateComponent;
    let fixture: ComponentFixture<MedicalInvoiceUpdateComponent>;
    let service: MedicalInvoiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [MedicalInvoiceUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MedicalInvoiceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedicalInvoiceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedicalInvoiceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MedicalInvoice(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MedicalInvoice();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
