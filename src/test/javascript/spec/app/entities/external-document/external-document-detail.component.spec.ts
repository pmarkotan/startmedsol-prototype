import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { ExternalDocumentDetailComponent } from 'app/entities/external-document/external-document-detail.component';
import { ExternalDocument } from 'app/shared/model/external-document.model';

describe('Component Tests', () => {
  describe('ExternalDocument Management Detail Component', () => {
    let comp: ExternalDocumentDetailComponent;
    let fixture: ComponentFixture<ExternalDocumentDetailComponent>;
    const route = ({ data: of({ externalDocument: new ExternalDocument(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [ExternalDocumentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ExternalDocumentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ExternalDocumentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load externalDocument on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.externalDocument).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
