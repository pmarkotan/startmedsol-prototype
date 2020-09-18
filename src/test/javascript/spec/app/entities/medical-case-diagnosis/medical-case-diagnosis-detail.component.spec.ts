import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { MedicalCaseDiagnosisDetailComponent } from 'app/entities/medical-case-diagnosis/medical-case-diagnosis-detail.component';
import { MedicalCaseDiagnosis } from 'app/shared/model/medical-case-diagnosis.model';

describe('Component Tests', () => {
  describe('MedicalCaseDiagnosis Management Detail Component', () => {
    let comp: MedicalCaseDiagnosisDetailComponent;
    let fixture: ComponentFixture<MedicalCaseDiagnosisDetailComponent>;
    const route = ({ data: of({ medicalCaseDiagnosis: new MedicalCaseDiagnosis(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [MedicalCaseDiagnosisDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MedicalCaseDiagnosisDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MedicalCaseDiagnosisDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load medicalCaseDiagnosis on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.medicalCaseDiagnosis).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
