import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { CsCountryDetailComponent } from 'app/entities/cs-country/cs-country-detail.component';
import { CsCountry } from 'app/shared/model/cs-country.model';

describe('Component Tests', () => {
  describe('CsCountry Management Detail Component', () => {
    let comp: CsCountryDetailComponent;
    let fixture: ComponentFixture<CsCountryDetailComponent>;
    const route = ({ data: of({ csCountry: new CsCountry(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [CsCountryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CsCountryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CsCountryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load csCountry on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.csCountry).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
