import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { CsDiagnosisDetailComponent } from 'app/entities/cs-diagnosis/cs-diagnosis-detail.component';
import { CsDiagnosis } from 'app/shared/model/cs-diagnosis.model';

describe('Component Tests', () => {
  describe('CsDiagnosis Management Detail Component', () => {
    let comp: CsDiagnosisDetailComponent;
    let fixture: ComponentFixture<CsDiagnosisDetailComponent>;
    const route = ({ data: of({ csDiagnosis: new CsDiagnosis(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [CsDiagnosisDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CsDiagnosisDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CsDiagnosisDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load csDiagnosis on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.csDiagnosis).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
