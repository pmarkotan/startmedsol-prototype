import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { DynamicFormUpdateComponent } from 'app/entities/dynamic-form/dynamic-form-update.component';
import { DynamicFormService } from 'app/entities/dynamic-form/dynamic-form.service';
import { DynamicForm } from 'app/shared/model/dynamic-form.model';

describe('Component Tests', () => {
  describe('DynamicForm Management Update Component', () => {
    let comp: DynamicFormUpdateComponent;
    let fixture: ComponentFixture<DynamicFormUpdateComponent>;
    let service: DynamicFormService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [DynamicFormUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DynamicFormUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DynamicFormUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DynamicFormService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DynamicForm(123);
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
        const entity = new DynamicForm();
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
