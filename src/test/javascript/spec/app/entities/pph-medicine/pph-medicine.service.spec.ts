import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PphMedicineService } from 'app/entities/pph-medicine/pph-medicine.service';
import { IPphMedicine, PphMedicine } from 'app/shared/model/pph-medicine.model';
import { PrescriptionType } from 'app/shared/model/enumerations/prescription-type.model';
import { MedicineType } from 'app/shared/model/enumerations/medicine-type.model';
import { MedicineStatus } from 'app/shared/model/enumerations/medicine-status.model';

describe('Service Tests', () => {
  describe('PphMedicine Service', () => {
    let injector: TestBed;
    let service: PphMedicineService;
    let httpMock: HttpTestingController;
    let elemDefault: IPphMedicine;
    let expectedResult: IPphMedicine | IPphMedicine[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PphMedicineService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new PphMedicine(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        currentDate,
        false,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        false,
        'AAAAAAA',
        0,
        0,
        0,
        false,
        false,
        false,
        false,
        false,
        0,
        0,
        PrescriptionType.WITHOUT_PRESCRIPTION,
        MedicineType.MEDICINE,
        MedicineStatus.NEW_PRODUCT,
        false,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        false
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            mbookDeletedDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a PphMedicine', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            mbookDeletedDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            mbookDeletedDate: currentDate,
          },
          returnedFromService
        );

        service.create(new PphMedicine()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PphMedicine', () => {
        const returnedFromService = Object.assign(
          {
            productId: 1,
            masterBookId: 'BBBBBB',
            name: 'BBBBBB',
            packageDescription: 'BBBBBB',
            eanCode: 'BBBBBB',
            atcCode: 'BBBBBB',
            brand: 'BBBBBB',
            activeSubstance: 'BBBBBB',
            potency: 'BBBBBB',
            substanceAmountTotal: 1,
            doseInPackage: 1,
            substanceAmount: 1,
            doseMsmUnit: 'BBBBBB',
            substanceMsmUnit: 'BBBBBB',
            packageSize: 1,
            packageMsmUnit: 'BBBBBB',
            dailyDoze: 1,
            dailyDozeMsmUnit: 'BBBBBB',
            ddMsmUnitFactor: 1,
            daysOfTherapy: 1,
            consumerPrice: 1,
            grossConsumerPrice: 1,
            efficacy: 'BBBBBB',
            oldName: 'BBBBBB',
            oepTtt: 'BBBBBB',
            masterBookDeleted: true,
            mbookDeletedDate: currentDate.format(DATE_TIME_FORMAT),
            available: true,
            motivationStatus: 1,
            costEffectivityCode: 1,
            dailyTherapyCost: 1,
            equivalenceGroupId: 1,
            oldSubsidyType: 'BBBBBB',
            preferredPriceFlag: 1,
            pharmacyFlag: 'BBBBBB',
            customMade: true,
            medicalDeviceIso: 'BBBBBB',
            producerPrice: 1,
            wholesalePrice: 1,
            vatRate: 1,
            armyPrescription: true,
            publicSupply: true,
            workAccidentPrescr: true,
            extraSubsidyPrescr: true,
            medicalSubsidyPrescr: true,
            substancePrice: 1,
            includeInHc2: 1,
            prescriptionType: 'BBBBBB',
            medicineType: 'BBBBBB',
            medicineStatus: 'BBBBBB',
            normative: true,
            ogyiId: 1,
            normFixGroupId: 1,
            extraSubsidyFixGroupId: 1,
            medicalSubsidyPrescriptionFixGroupId: 1,
            dosageMod: 'BBBBBB',
            activePuphaData: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            mbookDeletedDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of PphMedicine', () => {
        const returnedFromService = Object.assign(
          {
            productId: 1,
            masterBookId: 'BBBBBB',
            name: 'BBBBBB',
            packageDescription: 'BBBBBB',
            eanCode: 'BBBBBB',
            atcCode: 'BBBBBB',
            brand: 'BBBBBB',
            activeSubstance: 'BBBBBB',
            potency: 'BBBBBB',
            substanceAmountTotal: 1,
            doseInPackage: 1,
            substanceAmount: 1,
            doseMsmUnit: 'BBBBBB',
            substanceMsmUnit: 'BBBBBB',
            packageSize: 1,
            packageMsmUnit: 'BBBBBB',
            dailyDoze: 1,
            dailyDozeMsmUnit: 'BBBBBB',
            ddMsmUnitFactor: 1,
            daysOfTherapy: 1,
            consumerPrice: 1,
            grossConsumerPrice: 1,
            efficacy: 'BBBBBB',
            oldName: 'BBBBBB',
            oepTtt: 'BBBBBB',
            masterBookDeleted: true,
            mbookDeletedDate: currentDate.format(DATE_TIME_FORMAT),
            available: true,
            motivationStatus: 1,
            costEffectivityCode: 1,
            dailyTherapyCost: 1,
            equivalenceGroupId: 1,
            oldSubsidyType: 'BBBBBB',
            preferredPriceFlag: 1,
            pharmacyFlag: 'BBBBBB',
            customMade: true,
            medicalDeviceIso: 'BBBBBB',
            producerPrice: 1,
            wholesalePrice: 1,
            vatRate: 1,
            armyPrescription: true,
            publicSupply: true,
            workAccidentPrescr: true,
            extraSubsidyPrescr: true,
            medicalSubsidyPrescr: true,
            substancePrice: 1,
            includeInHc2: 1,
            prescriptionType: 'BBBBBB',
            medicineType: 'BBBBBB',
            medicineStatus: 'BBBBBB',
            normative: true,
            ogyiId: 1,
            normFixGroupId: 1,
            extraSubsidyFixGroupId: 1,
            medicalSubsidyPrescriptionFixGroupId: 1,
            dosageMod: 'BBBBBB',
            activePuphaData: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            mbookDeletedDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a PphMedicine', () => {
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
