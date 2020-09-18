import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { PphMedicineQualifiedNameComponent } from 'app/entities/pph-medicine-qualified-name/pph-medicine-qualified-name.component';
import { PphMedicineQualifiedNameService } from 'app/entities/pph-medicine-qualified-name/pph-medicine-qualified-name.service';
import { PphMedicineQualifiedName } from 'app/shared/model/pph-medicine-qualified-name.model';

describe('Component Tests', () => {
  describe('PphMedicineQualifiedName Management Component', () => {
    let comp: PphMedicineQualifiedNameComponent;
    let fixture: ComponentFixture<PphMedicineQualifiedNameComponent>;
    let service: PphMedicineQualifiedNameService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [PphMedicineQualifiedNameComponent],
      })
        .overrideTemplate(PphMedicineQualifiedNameComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PphMedicineQualifiedNameComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PphMedicineQualifiedNameService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PphMedicineQualifiedName(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.pphMedicineQualifiedNames && comp.pphMedicineQualifiedNames[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
