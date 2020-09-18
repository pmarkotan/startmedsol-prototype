import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { DictionaryTranslationDetailComponent } from 'app/entities/dictionary-translation/dictionary-translation-detail.component';
import { DictionaryTranslation } from 'app/shared/model/dictionary-translation.model';

describe('Component Tests', () => {
  describe('DictionaryTranslation Management Detail Component', () => {
    let comp: DictionaryTranslationDetailComponent;
    let fixture: ComponentFixture<DictionaryTranslationDetailComponent>;
    const route = ({ data: of({ dictionaryTranslation: new DictionaryTranslation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [DictionaryTranslationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DictionaryTranslationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DictionaryTranslationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dictionaryTranslation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dictionaryTranslation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
