import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { CsPostalCodeUpdateComponent } from 'app/entities/cs-postal-code/cs-postal-code-update.component';
import { CsPostalCodeService } from 'app/entities/cs-postal-code/cs-postal-code.service';
import { CsPostalCode } from 'app/shared/model/cs-postal-code.model';

describe('Component Tests', () => {
  describe('CsPostalCode Management Update Component', () => {
    let comp: CsPostalCodeUpdateComponent;
    let fixture: ComponentFixture<CsPostalCodeUpdateComponent>;
    let service: CsPostalCodeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [CsPostalCodeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CsPostalCodeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CsPostalCodeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CsPostalCodeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CsPostalCode(123);
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
        const entity = new CsPostalCode();
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
