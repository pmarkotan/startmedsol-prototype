import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { MedicalCaseDiagnosisUpdateComponent } from 'app/entities/medical-case-diagnosis/medical-case-diagnosis-update.component';
import { MedicalCaseDiagnosisService } from 'app/entities/medical-case-diagnosis/medical-case-diagnosis.service';
import { MedicalCaseDiagnosis } from 'app/shared/model/medical-case-diagnosis.model';

describe('Component Tests', () => {
  describe('MedicalCaseDiagnosis Management Update Component', () => {
    let comp: MedicalCaseDiagnosisUpdateComponent;
    let fixture: ComponentFixture<MedicalCaseDiagnosisUpdateComponent>;
    let service: MedicalCaseDiagnosisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [MedicalCaseDiagnosisUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MedicalCaseDiagnosisUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedicalCaseDiagnosisUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedicalCaseDiagnosisService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MedicalCaseDiagnosis(123);
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
        const entity = new MedicalCaseDiagnosis();
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
