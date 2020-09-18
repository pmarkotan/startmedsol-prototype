import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { DictionaryItemUpdateComponent } from 'app/entities/dictionary-item/dictionary-item-update.component';
import { DictionaryItemService } from 'app/entities/dictionary-item/dictionary-item.service';
import { DictionaryItem } from 'app/shared/model/dictionary-item.model';

describe('Component Tests', () => {
  describe('DictionaryItem Management Update Component', () => {
    let comp: DictionaryItemUpdateComponent;
    let fixture: ComponentFixture<DictionaryItemUpdateComponent>;
    let service: DictionaryItemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [DictionaryItemUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DictionaryItemUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DictionaryItemUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DictionaryItemService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DictionaryItem(123);
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
        const entity = new DictionaryItem();
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
