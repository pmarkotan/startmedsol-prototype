import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { EhrDocumentService } from 'app/entities/ehr-document/ehr-document.service';
import { IEhrDocument, EhrDocument } from 'app/shared/model/ehr-document.model';

describe('Service Tests', () => {
  describe('EhrDocument Service', () => {
    let injector: TestBed;
    let service: EhrDocumentService;
    let httpMock: HttpTestingController;
    let elemDefault: IEhrDocument;
    let expectedResult: IEhrDocument | IEhrDocument[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EhrDocumentService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new EhrDocument(0, 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            created: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EhrDocument', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            created: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            created: currentDate,
          },
          returnedFromService
        );

        service.create(new EhrDocument()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EhrDocument', () => {
        const returnedFromService = Object.assign(
          {
            eesztId: 'BBBBBB',
            documentId: 'BBBBBB',
            created: currentDate.format(DATE_TIME_FORMAT),
            documentType: 'BBBBBB',
            doctorName: 'BBBBBB',
            institutionName: 'BBBBBB',
            praxisName: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            created: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EhrDocument', () => {
        const returnedFromService = Object.assign(
          {
            eesztId: 'BBBBBB',
            documentId: 'BBBBBB',
            created: currentDate.format(DATE_TIME_FORMAT),
            documentType: 'BBBBBB',
            doctorName: 'BBBBBB',
            institutionName: 'BBBBBB',
            praxisName: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            created: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a EhrDocument', () => {
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
