import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { BillingInformationDetailComponent } from 'app/entities/billing-information/billing-information-detail.component';
import { BillingInformation } from 'app/shared/model/billing-information.model';

describe('Component Tests', () => {
  describe('BillingInformation Management Detail Component', () => {
    let comp: BillingInformationDetailComponent;
    let fixture: ComponentFixture<BillingInformationDetailComponent>;
    const route = ({ data: of({ billingInformation: new BillingInformation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [BillingInformationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BillingInformationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BillingInformationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load billingInformation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.billingInformation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
