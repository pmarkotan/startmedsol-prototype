import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { EhrDocumentDetailComponent } from 'app/entities/ehr-document/ehr-document-detail.component';
import { EhrDocument } from 'app/shared/model/ehr-document.model';

describe('Component Tests', () => {
  describe('EhrDocument Management Detail Component', () => {
    let comp: EhrDocumentDetailComponent;
    let fixture: ComponentFixture<EhrDocumentDetailComponent>;
    const route = ({ data: of({ ehrDocument: new EhrDocument(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [EhrDocumentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EhrDocumentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EhrDocumentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ehrDocument on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ehrDocument).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
