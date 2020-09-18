import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { CsProcedureDetailComponent } from 'app/entities/cs-procedure/cs-procedure-detail.component';
import { CsProcedure } from 'app/shared/model/cs-procedure.model';

describe('Component Tests', () => {
  describe('CsProcedure Management Detail Component', () => {
    let comp: CsProcedureDetailComponent;
    let fixture: ComponentFixture<CsProcedureDetailComponent>;
    const route = ({ data: of({ csProcedure: new CsProcedure(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [CsProcedureDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CsProcedureDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CsProcedureDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load csProcedure on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.csProcedure).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
