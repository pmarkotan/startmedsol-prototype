import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CsDiagnosisService } from 'app/entities/cs-diagnosis/cs-diagnosis.service';
import { ICsDiagnosis, CsDiagnosis } from 'app/shared/model/cs-diagnosis.model';
import { DgSex } from 'app/shared/model/enumerations/dg-sex.model';

describe('Service Tests', () => {
  describe('CsDiagnosis Service', () => {
    let injector: TestBed;
    let service: CsDiagnosisService;
    let httpMock: HttpTestingController;
    let elemDefault: ICsDiagnosis;
    let expectedResult: ICsDiagnosis | ICsDiagnosis[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CsDiagnosisService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new CsDiagnosis(0, 'AAAAAAA', false, 'AAAAAAA', DgSex.BOTH, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CsDiagnosis', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new CsDiagnosis()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CsDiagnosis', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            report: true,
            descr: 'BBBBBB',
            sex: 'BBBBBB',
            ageMin: 1,
            ageMax: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CsDiagnosis', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            report: true,
            descr: 'BBBBBB',
            sex: 'BBBBBB',
            ageMin: 1,
            ageMax: 1,
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

      it('should delete a CsDiagnosis', () => {
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
