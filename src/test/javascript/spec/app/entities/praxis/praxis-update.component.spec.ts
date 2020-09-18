import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { PraxisUpdateComponent } from 'app/entities/praxis/praxis-update.component';
import { PraxisService } from 'app/entities/praxis/praxis.service';
import { Praxis } from 'app/shared/model/praxis.model';

describe('Component Tests', () => {
  describe('Praxis Management Update Component', () => {
    let comp: PraxisUpdateComponent;
    let fixture: ComponentFixture<PraxisUpdateComponent>;
    let service: PraxisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [PraxisUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PraxisUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PraxisUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PraxisService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Praxis(123);
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
        const entity = new Praxis();
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
