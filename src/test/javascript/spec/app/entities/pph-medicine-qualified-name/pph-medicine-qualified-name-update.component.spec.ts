import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { PphMedicineQualifiedNameUpdateComponent } from 'app/entities/pph-medicine-qualified-name/pph-medicine-qualified-name-update.component';
import { PphMedicineQualifiedNameService } from 'app/entities/pph-medicine-qualified-name/pph-medicine-qualified-name.service';
import { PphMedicineQualifiedName } from 'app/shared/model/pph-medicine-qualified-name.model';

describe('Component Tests', () => {
  describe('PphMedicineQualifiedName Management Update Component', () => {
    let comp: PphMedicineQualifiedNameUpdateComponent;
    let fixture: ComponentFixture<PphMedicineQualifiedNameUpdateComponent>;
    let service: PphMedicineQualifiedNameService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [PphMedicineQualifiedNameUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PphMedicineQualifiedNameUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PphMedicineQualifiedNameUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PphMedicineQualifiedNameService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PphMedicineQualifiedName(123);
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
        const entity = new PphMedicineQualifiedName();
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
