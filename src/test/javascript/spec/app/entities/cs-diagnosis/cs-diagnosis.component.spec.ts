import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { CsDiagnosisComponent } from 'app/entities/cs-diagnosis/cs-diagnosis.component';
import { CsDiagnosisService } from 'app/entities/cs-diagnosis/cs-diagnosis.service';
import { CsDiagnosis } from 'app/shared/model/cs-diagnosis.model';

describe('Component Tests', () => {
  describe('CsDiagnosis Management Component', () => {
    let comp: CsDiagnosisComponent;
    let fixture: ComponentFixture<CsDiagnosisComponent>;
    let service: CsDiagnosisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [CsDiagnosisComponent],
      })
        .overrideTemplate(CsDiagnosisComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CsDiagnosisComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CsDiagnosisService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CsDiagnosis(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.csDiagnoses && comp.csDiagnoses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
