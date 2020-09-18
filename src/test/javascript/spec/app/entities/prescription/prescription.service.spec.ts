import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PrescriptionService } from 'app/entities/prescription/prescription.service';
import { IPrescription, Prescription } from 'app/shared/model/prescription.model';
import { DoctorQualificationValidatorRuleType } from 'app/shared/model/enumerations/doctor-qualification-validator-rule-type.model';
import { PrescriptionSubsidyCategory } from 'app/shared/model/enumerations/prescription-subsidy-category.model';
import { PrescriptionStatus } from 'app/shared/model/enumerations/prescription-status.model';
import { EesztSendingStatus } from 'app/shared/model/enumerations/eeszt-sending-status.model';
import { MedicalProductType } from 'app/shared/model/enumerations/medical-product-type.model';
import { Frequency } from 'app/shared/model/enumerations/frequency.model';

describe('Service Tests', () => {
  describe('Prescription Service', () => {
    let injector: TestBed;
    let service: PrescriptionService;
    let httpMock: HttpTestingController;
    let elemDefault: IPrescription;
    let expectedResult: IPrescription | IPrescription[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PrescriptionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Prescription(
        0,
        false,
        false,
        false,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        DoctorQualificationValidatorRuleType.WITHOUT_QUALIFICATION_RULE,
        PrescriptionSubsidyCategory.NORMATIVE,
        PrescriptionStatus.PREPARED,
        EesztSendingStatus.SUCCESSFUL,
        MedicalProductType.MEDICINE,
        'AAAAAAA',
        'AAAAAAA',
        Frequency.DAILY,
        0,
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        false,
        0,
        false,
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            inscriptionDate: currentDate.format(DATE_TIME_FORMAT),
            proposalDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Prescription', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            inscriptionDate: currentDate.format(DATE_TIME_FORMAT),
            proposalDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            inscriptionDate: currentDate,
            proposalDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Prescription()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Prescription', () => {
        const returnedFromService = Object.assign(
          {
            openedPackage: true,
            medicalAidTeachPrescribing: true,
            forH2cCertificate: true,
            inscriptionDate: currentDate.format(DATE_TIME_FORMAT),
            causeOfNotReplaceability: 'BBBBBB',
            description: 'BBBBBB',
            doseMsmUnit: 'BBBBBB',
            eesztGroupId: 'BBBBBB',
            institution: 'BBBBBB',
            instructions: 'BBBBBB',
            integrationId: 'BBBBBB',
            equippedWith: 1,
            acceptedQualificationRule: 'BBBBBB',
            subsidyCategory: 'BBBBBB',
            status: 'BBBBBB',
            eesztSendingStatus: 'BBBBBB',
            medicalProductType: 'BBBBBB',
            preparationDescription: 'BBBBBB',
            invalidationReason: 'BBBBBB',
            frequency: 'BBBBBB',
            frequencyMultiplier: 1,
            quantityMultiplier: 1,
            morning: 1,
            noon: 1,
            evening: 1,
            beforeSleep: 1,
            dosagePatternText: 'BBBBBB',
            quantity: 1,
            quantityCause: 'BBBBBB',
            forSeveralMonths: true,
            monthsSuppliedFor: 1,
            forOnePrescription: true,
            specialistRegNo: 'BBBBBB',
            proposalDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            inscriptionDate: currentDate,
            proposalDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Prescription', () => {
        const returnedFromService = Object.assign(
          {
            openedPackage: true,
            medicalAidTeachPrescribing: true,
            forH2cCertificate: true,
            inscriptionDate: currentDate.format(DATE_TIME_FORMAT),
            causeOfNotReplaceability: 'BBBBBB',
            description: 'BBBBBB',
            doseMsmUnit: 'BBBBBB',
            eesztGroupId: 'BBBBBB',
            institution: 'BBBBBB',
            instructions: 'BBBBBB',
            integrationId: 'BBBBBB',
            equippedWith: 1,
            acceptedQualificationRule: 'BBBBBB',
            subsidyCategory: 'BBBBBB',
            status: 'BBBBBB',
            eesztSendingStatus: 'BBBBBB',
            medicalProductType: 'BBBBBB',
            preparationDescription: 'BBBBBB',
            invalidationReason: 'BBBBBB',
            frequency: 'BBBBBB',
            frequencyMultiplier: 1,
            quantityMultiplier: 1,
            morning: 1,
            noon: 1,
            evening: 1,
            beforeSleep: 1,
            dosagePatternText: 'BBBBBB',
            quantity: 1,
            quantityCause: 'BBBBBB',
            forSeveralMonths: true,
            monthsSuppliedFor: 1,
            forOnePrescription: true,
            specialistRegNo: 'BBBBBB',
            proposalDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            inscriptionDate: currentDate,
            proposalDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Prescription', () => {
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
