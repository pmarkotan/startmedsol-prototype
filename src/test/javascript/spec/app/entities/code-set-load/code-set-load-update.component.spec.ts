import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { CodeSetLoadUpdateComponent } from 'app/entities/code-set-load/code-set-load-update.component';
import { CodeSetLoadService } from 'app/entities/code-set-load/code-set-load.service';
import { CodeSetLoad } from 'app/shared/model/code-set-load.model';

describe('Component Tests', () => {
  describe('CodeSetLoad Management Update Component', () => {
    let comp: CodeSetLoadUpdateComponent;
    let fixture: ComponentFixture<CodeSetLoadUpdateComponent>;
    let service: CodeSetLoadService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [CodeSetLoadUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CodeSetLoadUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CodeSetLoadUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CodeSetLoadService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CodeSetLoad(123);
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
        const entity = new CodeSetLoad();
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
