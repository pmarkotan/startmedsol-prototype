import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { CsCountryComponent } from 'app/entities/cs-country/cs-country.component';
import { CsCountryService } from 'app/entities/cs-country/cs-country.service';
import { CsCountry } from 'app/shared/model/cs-country.model';

describe('Component Tests', () => {
  describe('CsCountry Management Component', () => {
    let comp: CsCountryComponent;
    let fixture: ComponentFixture<CsCountryComponent>;
    let service: CsCountryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [CsCountryComponent],
      })
        .overrideTemplate(CsCountryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CsCountryComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CsCountryService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CsCountry(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.csCountries && comp.csCountries[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
