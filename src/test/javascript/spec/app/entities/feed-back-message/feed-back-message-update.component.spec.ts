import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { FeedBackMessageUpdateComponent } from 'app/entities/feed-back-message/feed-back-message-update.component';
import { FeedBackMessageService } from 'app/entities/feed-back-message/feed-back-message.service';
import { FeedBackMessage } from 'app/shared/model/feed-back-message.model';

describe('Component Tests', () => {
  describe('FeedBackMessage Management Update Component', () => {
    let comp: FeedBackMessageUpdateComponent;
    let fixture: ComponentFixture<FeedBackMessageUpdateComponent>;
    let service: FeedBackMessageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [FeedBackMessageUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FeedBackMessageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FeedBackMessageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FeedBackMessageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FeedBackMessage(123);
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
        const entity = new FeedBackMessage();
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
