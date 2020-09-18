import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { CsPostalCodeComponent } from 'app/entities/cs-postal-code/cs-postal-code.component';
import { CsPostalCodeService } from 'app/entities/cs-postal-code/cs-postal-code.service';
import { CsPostalCode } from 'app/shared/model/cs-postal-code.model';

describe('Component Tests', () => {
  describe('CsPostalCode Management Component', () => {
    let comp: CsPostalCodeComponent;
    let fixture: ComponentFixture<CsPostalCodeComponent>;
    let service: CsPostalCodeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [CsPostalCodeComponent],
      })
        .overrideTemplate(CsPostalCodeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CsPostalCodeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CsPostalCodeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CsPostalCode(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.csPostalCodes && comp.csPostalCodes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
