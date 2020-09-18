import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { NavSettingsUpdateComponent } from 'app/entities/nav-settings/nav-settings-update.component';
import { NavSettingsService } from 'app/entities/nav-settings/nav-settings.service';
import { NavSettings } from 'app/shared/model/nav-settings.model';

describe('Component Tests', () => {
  describe('NavSettings Management Update Component', () => {
    let comp: NavSettingsUpdateComponent;
    let fixture: ComponentFixture<NavSettingsUpdateComponent>;
    let service: NavSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [NavSettingsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NavSettingsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NavSettingsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NavSettingsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NavSettings(123);
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
        const entity = new NavSettings();
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
