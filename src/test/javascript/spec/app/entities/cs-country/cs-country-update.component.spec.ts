import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { CsCountryUpdateComponent } from 'app/entities/cs-country/cs-country-update.component';
import { CsCountryService } from 'app/entities/cs-country/cs-country.service';
import { CsCountry } from 'app/shared/model/cs-country.model';

describe('Component Tests', () => {
  describe('CsCountry Management Update Component', () => {
    let comp: CsCountryUpdateComponent;
    let fixture: ComponentFixture<CsCountryUpdateComponent>;
    let service: CsCountryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [CsCountryUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CsCountryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CsCountryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CsCountryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CsCountry(123);
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
        const entity = new CsCountry();
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
