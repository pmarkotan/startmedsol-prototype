import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { SpecialistsAdviceUpdateComponent } from 'app/entities/specialists-advice/specialists-advice-update.component';
import { SpecialistsAdviceService } from 'app/entities/specialists-advice/specialists-advice.service';
import { SpecialistsAdvice } from 'app/shared/model/specialists-advice.model';

describe('Component Tests', () => {
  describe('SpecialistsAdvice Management Update Component', () => {
    let comp: SpecialistsAdviceUpdateComponent;
    let fixture: ComponentFixture<SpecialistsAdviceUpdateComponent>;
    let service: SpecialistsAdviceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [SpecialistsAdviceUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SpecialistsAdviceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SpecialistsAdviceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SpecialistsAdviceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SpecialistsAdvice(123);
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
        const entity = new SpecialistsAdvice();
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
