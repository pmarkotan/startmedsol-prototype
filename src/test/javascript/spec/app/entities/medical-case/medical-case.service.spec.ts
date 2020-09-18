import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { MedicalCaseService } from 'app/entities/medical-case/medical-case.service';
import { IMedicalCase, MedicalCase } from 'app/shared/model/medical-case.model';
import { MedicalCaseStatus } from 'app/shared/model/enumerations/medical-case-status.model';
import { AttendanceType } from 'app/shared/model/enumerations/attendance-type.model';

describe('Service Tests', () => {
  describe('MedicalCase Service', () => {
    let injector: TestBed;
    let service: MedicalCaseService;
    let httpMock: HttpTestingController;
    let elemDefault: IMedicalCase;
    let expectedResult: IMedicalCase | IMedicalCase[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MedicalCaseService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new MedicalCase(
        0,
        false,
        false,
        'AAAAAAA',
        currentDate,
        currentDate,
        MedicalCaseStatus.ACTIVE,
        AttendanceType.TYPE_1_FIRST_SPECIALIST_CARE
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            admissionDate: currentDate.format(DATE_TIME_FORMAT),
            closeDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a MedicalCase', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            admissionDate: currentDate.format(DATE_TIME_FORMAT),
            closeDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            admissionDate: currentDate,
            closeDate: currentDate,
          },
          returnedFromService
        );

        service.create(new MedicalCase()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a MedicalCase', () => {
        const returnedFromService = Object.assign(
          {
            deleted: true,
            confidental: true,
            ambulentNumber: 'BBBBBB',
            admissionDate: currentDate.format(DATE_TIME_FORMAT),
            closeDate: currentDate.format(DATE_TIME_FORMAT),
            status: 'BBBBBB',
            attendanceType: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            admissionDate: currentDate,
            closeDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of MedicalCase', () => {
        const returnedFromService = Object.assign(
          {
            deleted: true,
            confidental: true,
            ambulentNumber: 'BBBBBB',
            admissionDate: currentDate.format(DATE_TIME_FORMAT),
            closeDate: currentDate.format(DATE_TIME_FORMAT),
            status: 'BBBBBB',
            attendanceType: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            admissionDate: currentDate,
            closeDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a MedicalCase', () => {
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
