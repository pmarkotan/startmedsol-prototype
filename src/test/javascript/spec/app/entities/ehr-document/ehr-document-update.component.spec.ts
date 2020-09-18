import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { EhrDocumentUpdateComponent } from 'app/entities/ehr-document/ehr-document-update.component';
import { EhrDocumentService } from 'app/entities/ehr-document/ehr-document.service';
import { EhrDocument } from 'app/shared/model/ehr-document.model';

describe('Component Tests', () => {
  describe('EhrDocument Management Update Component', () => {
    let comp: EhrDocumentUpdateComponent;
    let fixture: ComponentFixture<EhrDocumentUpdateComponent>;
    let service: EhrDocumentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [EhrDocumentUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EhrDocumentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EhrDocumentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EhrDocumentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EhrDocument(123);
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
        const entity = new EhrDocument();
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
