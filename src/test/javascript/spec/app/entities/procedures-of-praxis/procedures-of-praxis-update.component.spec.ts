import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { ProceduresOfPraxisUpdateComponent } from 'app/entities/procedures-of-praxis/procedures-of-praxis-update.component';
import { ProceduresOfPraxisService } from 'app/entities/procedures-of-praxis/procedures-of-praxis.service';
import { ProceduresOfPraxis } from 'app/shared/model/procedures-of-praxis.model';

describe('Component Tests', () => {
  describe('ProceduresOfPraxis Management Update Component', () => {
    let comp: ProceduresOfPraxisUpdateComponent;
    let fixture: ComponentFixture<ProceduresOfPraxisUpdateComponent>;
    let service: ProceduresOfPraxisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [ProceduresOfPraxisUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProceduresOfPraxisUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProceduresOfPraxisUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProceduresOfPraxisService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProceduresOfPraxis(123);
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
        const entity = new ProceduresOfPraxis();
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
