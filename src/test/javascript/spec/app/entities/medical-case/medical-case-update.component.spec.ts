import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { MedicalCaseUpdateComponent } from 'app/entities/medical-case/medical-case-update.component';
import { MedicalCaseService } from 'app/entities/medical-case/medical-case.service';
import { MedicalCase } from 'app/shared/model/medical-case.model';

describe('Component Tests', () => {
  describe('MedicalCase Management Update Component', () => {
    let comp: MedicalCaseUpdateComponent;
    let fixture: ComponentFixture<MedicalCaseUpdateComponent>;
    let service: MedicalCaseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [MedicalCaseUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MedicalCaseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedicalCaseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedicalCaseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MedicalCase(123);
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
        const entity = new MedicalCase();
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
