import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { DynamicFormDetailComponent } from 'app/entities/dynamic-form/dynamic-form-detail.component';
import { DynamicForm } from 'app/shared/model/dynamic-form.model';

describe('Component Tests', () => {
  describe('DynamicForm Management Detail Component', () => {
    let comp: DynamicFormDetailComponent;
    let fixture: ComponentFixture<DynamicFormDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ dynamicForm: new DynamicForm(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [DynamicFormDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DynamicFormDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DynamicFormDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load dynamicForm on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dynamicForm).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
