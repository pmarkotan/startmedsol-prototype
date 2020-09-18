import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { DataGeneratorUpdateComponent } from 'app/entities/data-generator/data-generator-update.component';
import { DataGeneratorService } from 'app/entities/data-generator/data-generator.service';
import { DataGenerator } from 'app/shared/model/data-generator.model';

describe('Component Tests', () => {
  describe('DataGenerator Management Update Component', () => {
    let comp: DataGeneratorUpdateComponent;
    let fixture: ComponentFixture<DataGeneratorUpdateComponent>;
    let service: DataGeneratorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [DataGeneratorUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DataGeneratorUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DataGeneratorUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DataGeneratorService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DataGenerator(123);
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
        const entity = new DataGenerator();
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
