import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { ErrorRecordDetailComponent } from 'app/entities/error-record/error-record-detail.component';
import { ErrorRecord } from 'app/shared/model/error-record.model';

describe('Component Tests', () => {
  describe('ErrorRecord Management Detail Component', () => {
    let comp: ErrorRecordDetailComponent;
    let fixture: ComponentFixture<ErrorRecordDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ errorRecord: new ErrorRecord(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [ErrorRecordDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ErrorRecordDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ErrorRecordDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load errorRecord on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.errorRecord).toEqual(jasmine.objectContaining({ id: 123 }));
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
