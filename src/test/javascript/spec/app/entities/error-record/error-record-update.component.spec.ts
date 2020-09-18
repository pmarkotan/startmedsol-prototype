import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { ErrorRecordUpdateComponent } from 'app/entities/error-record/error-record-update.component';
import { ErrorRecordService } from 'app/entities/error-record/error-record.service';
import { ErrorRecord } from 'app/shared/model/error-record.model';

describe('Component Tests', () => {
  describe('ErrorRecord Management Update Component', () => {
    let comp: ErrorRecordUpdateComponent;
    let fixture: ComponentFixture<ErrorRecordUpdateComponent>;
    let service: ErrorRecordService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [ErrorRecordUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ErrorRecordUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ErrorRecordUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ErrorRecordService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ErrorRecord(123);
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
        const entity = new ErrorRecord();
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
