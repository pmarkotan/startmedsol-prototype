import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { PphMedicineUpdateComponent } from 'app/entities/pph-medicine/pph-medicine-update.component';
import { PphMedicineService } from 'app/entities/pph-medicine/pph-medicine.service';
import { PphMedicine } from 'app/shared/model/pph-medicine.model';

describe('Component Tests', () => {
  describe('PphMedicine Management Update Component', () => {
    let comp: PphMedicineUpdateComponent;
    let fixture: ComponentFixture<PphMedicineUpdateComponent>;
    let service: PphMedicineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [PphMedicineUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PphMedicineUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PphMedicineUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PphMedicineService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PphMedicine(123);
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
        const entity = new PphMedicine();
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
