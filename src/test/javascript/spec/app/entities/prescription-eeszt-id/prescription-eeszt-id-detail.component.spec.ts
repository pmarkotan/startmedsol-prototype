import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { PrescriptionEesztIdDetailComponent } from 'app/entities/prescription-eeszt-id/prescription-eeszt-id-detail.component';
import { PrescriptionEesztId } from 'app/shared/model/prescription-eeszt-id.model';

describe('Component Tests', () => {
  describe('PrescriptionEesztId Management Detail Component', () => {
    let comp: PrescriptionEesztIdDetailComponent;
    let fixture: ComponentFixture<PrescriptionEesztIdDetailComponent>;
    const route = ({ data: of({ prescriptionEesztId: new PrescriptionEesztId(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [PrescriptionEesztIdDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PrescriptionEesztIdDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PrescriptionEesztIdDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load prescriptionEesztId on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.prescriptionEesztId).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
