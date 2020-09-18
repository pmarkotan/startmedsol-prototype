import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { PuphaLoaderUpdateComponent } from 'app/entities/pupha-loader/pupha-loader-update.component';
import { PuphaLoaderService } from 'app/entities/pupha-loader/pupha-loader.service';
import { PuphaLoader } from 'app/shared/model/pupha-loader.model';

describe('Component Tests', () => {
  describe('PuphaLoader Management Update Component', () => {
    let comp: PuphaLoaderUpdateComponent;
    let fixture: ComponentFixture<PuphaLoaderUpdateComponent>;
    let service: PuphaLoaderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [PuphaLoaderUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PuphaLoaderUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PuphaLoaderUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PuphaLoaderService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PuphaLoader(123);
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
        const entity = new PuphaLoader();
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
