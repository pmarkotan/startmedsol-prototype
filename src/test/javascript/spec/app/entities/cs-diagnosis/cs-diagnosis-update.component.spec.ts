import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { CsDiagnosisUpdateComponent } from 'app/entities/cs-diagnosis/cs-diagnosis-update.component';
import { CsDiagnosisService } from 'app/entities/cs-diagnosis/cs-diagnosis.service';
import { CsDiagnosis } from 'app/shared/model/cs-diagnosis.model';

describe('Component Tests', () => {
  describe('CsDiagnosis Management Update Component', () => {
    let comp: CsDiagnosisUpdateComponent;
    let fixture: ComponentFixture<CsDiagnosisUpdateComponent>;
    let service: CsDiagnosisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [CsDiagnosisUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CsDiagnosisUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CsDiagnosisUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CsDiagnosisService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CsDiagnosis(123);
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
        const entity = new CsDiagnosis();
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
