import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { FeedBackMessageDetailComponent } from 'app/entities/feed-back-message/feed-back-message-detail.component';
import { FeedBackMessage } from 'app/shared/model/feed-back-message.model';

describe('Component Tests', () => {
  describe('FeedBackMessage Management Detail Component', () => {
    let comp: FeedBackMessageDetailComponent;
    let fixture: ComponentFixture<FeedBackMessageDetailComponent>;
    const route = ({ data: of({ feedBackMessage: new FeedBackMessage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [FeedBackMessageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FeedBackMessageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FeedBackMessageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load feedBackMessage on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.feedBackMessage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
