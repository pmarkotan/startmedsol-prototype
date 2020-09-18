import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CodeSetLoadService } from 'app/entities/code-set-load/code-set-load.service';
import { ICodeSetLoad, CodeSetLoad } from 'app/shared/model/code-set-load.model';
import { CodeSetType } from 'app/shared/model/enumerations/code-set-type.model';
import { CodeSetStatus } from 'app/shared/model/enumerations/code-set-status.model';

describe('Service Tests', () => {
  describe('CodeSetLoad Service', () => {
    let injector: TestBed;
    let service: CodeSetLoadService;
    let httpMock: HttpTestingController;
    let elemDefault: ICodeSetLoad;
    let expectedResult: ICodeSetLoad | ICodeSetLoad[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CodeSetLoadService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new CodeSetLoad(0, CodeSetType.BNO, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', CodeSetStatus.SUCCESS);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CodeSetLoad', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new CodeSetLoad()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CodeSetLoad', () => {
        const returnedFromService = Object.assign(
          {
            type: 'BBBBBB',
            latestVersion: 'BBBBBB',
            url: 'BBBBBB',
            log: 'BBBBBB',
            status: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CodeSetLoad', () => {
        const returnedFromService = Object.assign(
          {
            type: 'BBBBBB',
            latestVersion: 'BBBBBB',
            url: 'BBBBBB',
            log: 'BBBBBB',
            status: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CodeSetLoad', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
