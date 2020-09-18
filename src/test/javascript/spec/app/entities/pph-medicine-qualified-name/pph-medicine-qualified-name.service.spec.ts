import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PphMedicineQualifiedNameService } from 'app/entities/pph-medicine-qualified-name/pph-medicine-qualified-name.service';
import { IPphMedicineQualifiedName, PphMedicineQualifiedName } from 'app/shared/model/pph-medicine-qualified-name.model';

describe('Service Tests', () => {
  describe('PphMedicineQualifiedName Service', () => {
    let injector: TestBed;
    let service: PphMedicineQualifiedNameService;
    let httpMock: HttpTestingController;
    let elemDefault: IPphMedicineQualifiedName;
    let expectedResult: IPphMedicineQualifiedName | IPphMedicineQualifiedName[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PphMedicineQualifiedNameService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new PphMedicineQualifiedName(0, 'AAAAAAA', 'AAAAAAA', false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a PphMedicineQualifiedName', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new PphMedicineQualifiedName()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PphMedicineQualifiedName', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            activeSubstance: 'BBBBBB',
            activePuphaData: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of PphMedicineQualifiedName', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            activeSubstance: 'BBBBBB',
            activePuphaData: true,
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

      it('should delete a PphMedicineQualifiedName', () => {
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
