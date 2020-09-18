import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CsPostalCodeService } from 'app/entities/cs-postal-code/cs-postal-code.service';
import { ICsPostalCode, CsPostalCode } from 'app/shared/model/cs-postal-code.model';
import { PcRange } from 'app/shared/model/enumerations/pc-range.model';

describe('Service Tests', () => {
  describe('CsPostalCode Service', () => {
    let injector: TestBed;
    let service: CsPostalCodeService;
    let httpMock: HttpTestingController;
    let elemDefault: ICsPostalCode;
    let expectedResult: ICsPostalCode | ICsPostalCode[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CsPostalCodeService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new CsPostalCode(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', PcRange.NONE, 0, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CsPostalCode', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new CsPostalCode()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CsPostalCode', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            settlement: 'BBBBBB',
            part: 'BBBBBB',
            street: 'BBBBBB',
            kind: 'BBBBBB',
            rangeType: 'BBBBBB',
            rangeLo: 1,
            rangeHi: 1,
            district: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CsPostalCode', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            settlement: 'BBBBBB',
            part: 'BBBBBB',
            street: 'BBBBBB',
            kind: 'BBBBBB',
            rangeType: 'BBBBBB',
            rangeLo: 1,
            rangeHi: 1,
            district: 'BBBBBB',
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

      it('should delete a CsPostalCode', () => {
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
