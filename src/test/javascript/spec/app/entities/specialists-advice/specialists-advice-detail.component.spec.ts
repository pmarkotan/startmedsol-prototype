import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { SpecialistsAdviceDetailComponent } from 'app/entities/specialists-advice/specialists-advice-detail.component';
import { SpecialistsAdvice } from 'app/shared/model/specialists-advice.model';

describe('Component Tests', () => {
  describe('SpecialistsAdvice Management Detail Component', () => {
    let comp: SpecialistsAdviceDetailComponent;
    let fixture: ComponentFixture<SpecialistsAdviceDetailComponent>;
    const route = ({ data: of({ specialistsAdvice: new SpecialistsAdvice(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [SpecialistsAdviceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SpecialistsAdviceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SpecialistsAdviceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load specialistsAdvice on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.specialistsAdvice).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
