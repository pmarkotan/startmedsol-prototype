import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { PrescriptionEesztIdUpdateComponent } from 'app/entities/prescription-eeszt-id/prescription-eeszt-id-update.component';
import { PrescriptionEesztIdService } from 'app/entities/prescription-eeszt-id/prescription-eeszt-id.service';
import { PrescriptionEesztId } from 'app/shared/model/prescription-eeszt-id.model';

describe('Component Tests', () => {
  describe('PrescriptionEesztId Management Update Component', () => {
    let comp: PrescriptionEesztIdUpdateComponent;
    let fixture: ComponentFixture<PrescriptionEesztIdUpdateComponent>;
    let service: PrescriptionEesztIdService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [PrescriptionEesztIdUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PrescriptionEesztIdUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrescriptionEesztIdUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrescriptionEesztIdService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PrescriptionEesztId(123);
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
        const entity = new PrescriptionEesztId();
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
