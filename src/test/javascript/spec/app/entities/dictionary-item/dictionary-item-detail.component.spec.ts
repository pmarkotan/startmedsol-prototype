import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { DictionaryItemDetailComponent } from 'app/entities/dictionary-item/dictionary-item-detail.component';
import { DictionaryItem } from 'app/shared/model/dictionary-item.model';

describe('Component Tests', () => {
  describe('DictionaryItem Management Detail Component', () => {
    let comp: DictionaryItemDetailComponent;
    let fixture: ComponentFixture<DictionaryItemDetailComponent>;
    const route = ({ data: of({ dictionaryItem: new DictionaryItem(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [DictionaryItemDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DictionaryItemDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DictionaryItemDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dictionaryItem on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dictionaryItem).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
