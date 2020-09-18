import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { BillingInformationUpdateComponent } from 'app/entities/billing-information/billing-information-update.component';
import { BillingInformationService } from 'app/entities/billing-information/billing-information.service';
import { BillingInformation } from 'app/shared/model/billing-information.model';

describe('Component Tests', () => {
  describe('BillingInformation Management Update Component', () => {
    let comp: BillingInformationUpdateComponent;
    let fixture: ComponentFixture<BillingInformationUpdateComponent>;
    let service: BillingInformationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [BillingInformationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BillingInformationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BillingInformationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BillingInformationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BillingInformation(123);
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
        const entity = new BillingInformation();
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
