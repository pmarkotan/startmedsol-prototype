import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { MedicalCaseDetailComponent } from 'app/entities/medical-case/medical-case-detail.component';
import { MedicalCase } from 'app/shared/model/medical-case.model';

describe('Component Tests', () => {
  describe('MedicalCase Management Detail Component', () => {
    let comp: MedicalCaseDetailComponent;
    let fixture: ComponentFixture<MedicalCaseDetailComponent>;
    const route = ({ data: of({ medicalCase: new MedicalCase(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [MedicalCaseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MedicalCaseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MedicalCaseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load medicalCase on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.medicalCase).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
