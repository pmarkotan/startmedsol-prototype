import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { CodeSetLoadDetailComponent } from 'app/entities/code-set-load/code-set-load-detail.component';
import { CodeSetLoad } from 'app/shared/model/code-set-load.model';

describe('Component Tests', () => {
  describe('CodeSetLoad Management Detail Component', () => {
    let comp: CodeSetLoadDetailComponent;
    let fixture: ComponentFixture<CodeSetLoadDetailComponent>;
    const route = ({ data: of({ codeSetLoad: new CodeSetLoad(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [CodeSetLoadDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CodeSetLoadDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CodeSetLoadDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load codeSetLoad on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.codeSetLoad).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
