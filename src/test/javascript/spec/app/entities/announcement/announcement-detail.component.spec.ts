import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { AnnouncementDetailComponent } from 'app/entities/announcement/announcement-detail.component';
import { Announcement } from 'app/shared/model/announcement.model';

describe('Component Tests', () => {
  describe('Announcement Management Detail Component', () => {
    let comp: AnnouncementDetailComponent;
    let fixture: ComponentFixture<AnnouncementDetailComponent>;
    const route = ({ data: of({ announcement: new Announcement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [AnnouncementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AnnouncementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AnnouncementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load announcement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.announcement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
