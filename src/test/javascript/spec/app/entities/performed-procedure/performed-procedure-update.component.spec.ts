import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { PerformedProcedureUpdateComponent } from 'app/entities/performed-procedure/performed-procedure-update.component';
import { PerformedProcedureService } from 'app/entities/performed-procedure/performed-procedure.service';
import { PerformedProcedure } from 'app/shared/model/performed-procedure.model';

describe('Component Tests', () => {
  describe('PerformedProcedure Management Update Component', () => {
    let comp: PerformedProcedureUpdateComponent;
    let fixture: ComponentFixture<PerformedProcedureUpdateComponent>;
    let service: PerformedProcedureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [PerformedProcedureUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PerformedProcedureUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PerformedProcedureUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PerformedProcedureService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PerformedProcedure(123);
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
        const entity = new PerformedProcedure();
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
