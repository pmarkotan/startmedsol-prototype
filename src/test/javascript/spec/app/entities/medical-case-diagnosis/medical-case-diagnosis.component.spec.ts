import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { MedicalCaseDiagnosisComponent } from 'app/entities/medical-case-diagnosis/medical-case-diagnosis.component';
import { MedicalCaseDiagnosisService } from 'app/entities/medical-case-diagnosis/medical-case-diagnosis.service';
import { MedicalCaseDiagnosis } from 'app/shared/model/medical-case-diagnosis.model';

describe('Component Tests', () => {
  describe('MedicalCaseDiagnosis Management Component', () => {
    let comp: MedicalCaseDiagnosisComponent;
    let fixture: ComponentFixture<MedicalCaseDiagnosisComponent>;
    let service: MedicalCaseDiagnosisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [MedicalCaseDiagnosisComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(MedicalCaseDiagnosisComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedicalCaseDiagnosisComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedicalCaseDiagnosisService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MedicalCaseDiagnosis(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.medicalCaseDiagnoses && comp.medicalCaseDiagnoses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MedicalCaseDiagnosis(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.medicalCaseDiagnoses && comp.medicalCaseDiagnoses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
