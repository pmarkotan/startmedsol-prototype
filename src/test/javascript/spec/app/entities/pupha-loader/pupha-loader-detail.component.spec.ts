import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { PuphaLoaderDetailComponent } from 'app/entities/pupha-loader/pupha-loader-detail.component';
import { PuphaLoader } from 'app/shared/model/pupha-loader.model';

describe('Component Tests', () => {
  describe('PuphaLoader Management Detail Component', () => {
    let comp: PuphaLoaderDetailComponent;
    let fixture: ComponentFixture<PuphaLoaderDetailComponent>;
    const route = ({ data: of({ puphaLoader: new PuphaLoader(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [PuphaLoaderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PuphaLoaderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PuphaLoaderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load puphaLoader on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.puphaLoader).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
