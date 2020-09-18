import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { PraxisDetailComponent } from 'app/entities/praxis/praxis-detail.component';
import { Praxis } from 'app/shared/model/praxis.model';

describe('Component Tests', () => {
  describe('Praxis Management Detail Component', () => {
    let comp: PraxisDetailComponent;
    let fixture: ComponentFixture<PraxisDetailComponent>;
    const route = ({ data: of({ praxis: new Praxis(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [PraxisDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PraxisDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PraxisDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load praxis on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.praxis).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
