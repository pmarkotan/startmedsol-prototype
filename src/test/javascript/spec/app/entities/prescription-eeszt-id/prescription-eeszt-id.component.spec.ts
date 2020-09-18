import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { PrescriptionEesztIdComponent } from 'app/entities/prescription-eeszt-id/prescription-eeszt-id.component';
import { PrescriptionEesztIdService } from 'app/entities/prescription-eeszt-id/prescription-eeszt-id.service';
import { PrescriptionEesztId } from 'app/shared/model/prescription-eeszt-id.model';

describe('Component Tests', () => {
  describe('PrescriptionEesztId Management Component', () => {
    let comp: PrescriptionEesztIdComponent;
    let fixture: ComponentFixture<PrescriptionEesztIdComponent>;
    let service: PrescriptionEesztIdService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [PrescriptionEesztIdComponent],
      })
        .overrideTemplate(PrescriptionEesztIdComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrescriptionEesztIdComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrescriptionEesztIdService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PrescriptionEesztId(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.prescriptionEesztIds && comp.prescriptionEesztIds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
