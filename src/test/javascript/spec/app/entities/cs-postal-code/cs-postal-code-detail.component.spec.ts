import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { CsPostalCodeDetailComponent } from 'app/entities/cs-postal-code/cs-postal-code-detail.component';
import { CsPostalCode } from 'app/shared/model/cs-postal-code.model';

describe('Component Tests', () => {
  describe('CsPostalCode Management Detail Component', () => {
    let comp: CsPostalCodeDetailComponent;
    let fixture: ComponentFixture<CsPostalCodeDetailComponent>;
    const route = ({ data: of({ csPostalCode: new CsPostalCode(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [CsPostalCodeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CsPostalCodeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CsPostalCodeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load csPostalCode on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.csPostalCode).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
