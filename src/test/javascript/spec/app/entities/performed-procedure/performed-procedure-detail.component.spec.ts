import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { PerformedProcedureDetailComponent } from 'app/entities/performed-procedure/performed-procedure-detail.component';
import { PerformedProcedure } from 'app/shared/model/performed-procedure.model';

describe('Component Tests', () => {
  describe('PerformedProcedure Management Detail Component', () => {
    let comp: PerformedProcedureDetailComponent;
    let fixture: ComponentFixture<PerformedProcedureDetailComponent>;
    const route = ({ data: of({ performedProcedure: new PerformedProcedure(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [PerformedProcedureDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PerformedProcedureDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PerformedProcedureDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load performedProcedure on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.performedProcedure).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
