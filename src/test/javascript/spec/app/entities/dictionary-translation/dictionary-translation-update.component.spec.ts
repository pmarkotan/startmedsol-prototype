import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { DictionaryTranslationUpdateComponent } from 'app/entities/dictionary-translation/dictionary-translation-update.component';
import { DictionaryTranslationService } from 'app/entities/dictionary-translation/dictionary-translation.service';
import { DictionaryTranslation } from 'app/shared/model/dictionary-translation.model';

describe('Component Tests', () => {
  describe('DictionaryTranslation Management Update Component', () => {
    let comp: DictionaryTranslationUpdateComponent;
    let fixture: ComponentFixture<DictionaryTranslationUpdateComponent>;
    let service: DictionaryTranslationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [DictionaryTranslationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DictionaryTranslationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DictionaryTranslationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DictionaryTranslationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DictionaryTranslation(123);
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
        const entity = new DictionaryTranslation();
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
