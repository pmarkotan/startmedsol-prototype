import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { ProceduresOfPraxisDetailComponent } from 'app/entities/procedures-of-praxis/procedures-of-praxis-detail.component';
import { ProceduresOfPraxis } from 'app/shared/model/procedures-of-praxis.model';

describe('Component Tests', () => {
  describe('ProceduresOfPraxis Management Detail Component', () => {
    let comp: ProceduresOfPraxisDetailComponent;
    let fixture: ComponentFixture<ProceduresOfPraxisDetailComponent>;
    const route = ({ data: of({ proceduresOfPraxis: new ProceduresOfPraxis(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [ProceduresOfPraxisDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProceduresOfPraxisDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProceduresOfPraxisDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load proceduresOfPraxis on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proceduresOfPraxis).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
