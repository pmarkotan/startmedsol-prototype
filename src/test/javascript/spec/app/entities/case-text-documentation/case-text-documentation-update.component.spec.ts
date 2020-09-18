import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { CaseTextDocumentationUpdateComponent } from 'app/entities/case-text-documentation/case-text-documentation-update.component';
import { CaseTextDocumentationService } from 'app/entities/case-text-documentation/case-text-documentation.service';
import { CaseTextDocumentation } from 'app/shared/model/case-text-documentation.model';

describe('Component Tests', () => {
  describe('CaseTextDocumentation Management Update Component', () => {
    let comp: CaseTextDocumentationUpdateComponent;
    let fixture: ComponentFixture<CaseTextDocumentationUpdateComponent>;
    let service: CaseTextDocumentationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [CaseTextDocumentationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CaseTextDocumentationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CaseTextDocumentationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CaseTextDocumentationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CaseTextDocumentation(123);
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
        const entity = new CaseTextDocumentation();
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
