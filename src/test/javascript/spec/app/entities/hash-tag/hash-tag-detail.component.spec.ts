import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { HashTagDetailComponent } from 'app/entities/hash-tag/hash-tag-detail.component';
import { HashTag } from 'app/shared/model/hash-tag.model';

describe('Component Tests', () => {
  describe('HashTag Management Detail Component', () => {
    let comp: HashTagDetailComponent;
    let fixture: ComponentFixture<HashTagDetailComponent>;
    const route = ({ data: of({ hashTag: new HashTag(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [HashTagDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(HashTagDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(HashTagDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load hashTag on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.hashTag).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
