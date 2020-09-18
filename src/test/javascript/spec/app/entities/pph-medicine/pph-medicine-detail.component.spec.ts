import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { PphMedicineDetailComponent } from 'app/entities/pph-medicine/pph-medicine-detail.component';
import { PphMedicine } from 'app/shared/model/pph-medicine.model';

describe('Component Tests', () => {
  describe('PphMedicine Management Detail Component', () => {
    let comp: PphMedicineDetailComponent;
    let fixture: ComponentFixture<PphMedicineDetailComponent>;
    const route = ({ data: of({ pphMedicine: new PphMedicine(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [PphMedicineDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PphMedicineDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PphMedicineDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load pphMedicine on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.pphMedicine).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
