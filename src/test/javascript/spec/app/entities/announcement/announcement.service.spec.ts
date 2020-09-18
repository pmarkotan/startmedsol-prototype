import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { AnnouncementService } from 'app/entities/announcement/announcement.service';
import { IAnnouncement, Announcement } from 'app/shared/model/announcement.model';
import { AnnouncementLocation } from 'app/shared/model/enumerations/announcement-location.model';
import { AnnouncementType } from 'app/shared/model/enumerations/announcement-type.model';

describe('Service Tests', () => {
  describe('Announcement Service', () => {
    let injector: TestBed;
    let service: AnnouncementService;
    let httpMock: HttpTestingController;
    let elemDefault: IAnnouncement;
    let expectedResult: IAnnouncement | IAnnouncement[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AnnouncementService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Announcement(0, currentDate, currentDate, AnnouncementLocation.HOME_PAGE, AnnouncementType.NORMAL, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            publishingDate: currentDate.format(DATE_TIME_FORMAT),
            expireDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Announcement', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            publishingDate: currentDate.format(DATE_TIME_FORMAT),
            expireDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            publishingDate: currentDate,
            expireDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Announcement()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Announcement', () => {
        const returnedFromService = Object.assign(
          {
            publishingDate: currentDate.format(DATE_TIME_FORMAT),
            expireDate: currentDate.format(DATE_TIME_FORMAT),
            location: 'BBBBBB',
            type: 'BBBBBB',
            content: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            publishingDate: currentDate,
            expireDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Announcement', () => {
        const returnedFromService = Object.assign(
          {
            publishingDate: currentDate.format(DATE_TIME_FORMAT),
            expireDate: currentDate.format(DATE_TIME_FORMAT),
            location: 'BBBBBB',
            type: 'BBBBBB',
            content: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            publishingDate: currentDate,
            expireDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Announcement', () => {
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
