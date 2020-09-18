import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { ExternalDocumentUpdateComponent } from 'app/entities/external-document/external-document-update.component';
import { ExternalDocumentService } from 'app/entities/external-document/external-document.service';
import { ExternalDocument } from 'app/shared/model/external-document.model';

describe('Component Tests', () => {
  describe('ExternalDocument Management Update Component', () => {
    let comp: ExternalDocumentUpdateComponent;
    let fixture: ComponentFixture<ExternalDocumentUpdateComponent>;
    let service: ExternalDocumentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [ExternalDocumentUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ExternalDocumentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ExternalDocumentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ExternalDocumentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ExternalDocument(123);
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
        const entity = new ExternalDocument();
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
