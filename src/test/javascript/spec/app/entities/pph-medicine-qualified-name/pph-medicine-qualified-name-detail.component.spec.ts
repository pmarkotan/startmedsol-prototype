import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { PphMedicineQualifiedNameDetailComponent } from 'app/entities/pph-medicine-qualified-name/pph-medicine-qualified-name-detail.component';
import { PphMedicineQualifiedName } from 'app/shared/model/pph-medicine-qualified-name.model';

describe('Component Tests', () => {
  describe('PphMedicineQualifiedName Management Detail Component', () => {
    let comp: PphMedicineQualifiedNameDetailComponent;
    let fixture: ComponentFixture<PphMedicineQualifiedNameDetailComponent>;
    const route = ({ data: of({ pphMedicineQualifiedName: new PphMedicineQualifiedName(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [PphMedicineQualifiedNameDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PphMedicineQualifiedNameDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PphMedicineQualifiedNameDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load pphMedicineQualifiedName on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.pphMedicineQualifiedName).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
