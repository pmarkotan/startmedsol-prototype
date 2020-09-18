import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { ContactPersonUpdateComponent } from 'app/entities/contact-person/contact-person-update.component';
import { ContactPersonService } from 'app/entities/contact-person/contact-person.service';
import { ContactPerson } from 'app/shared/model/contact-person.model';

describe('Component Tests', () => {
  describe('ContactPerson Management Update Component', () => {
    let comp: ContactPersonUpdateComponent;
    let fixture: ComponentFixture<ContactPersonUpdateComponent>;
    let service: ContactPersonService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [ContactPersonUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ContactPersonUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContactPersonUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContactPersonService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ContactPerson(123);
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
        const entity = new ContactPerson();
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
