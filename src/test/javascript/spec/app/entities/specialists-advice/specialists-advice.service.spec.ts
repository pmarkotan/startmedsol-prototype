import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SpecialistsAdviceService } from 'app/entities/specialists-advice/specialists-advice.service';
import { ISpecialistsAdvice, SpecialistsAdvice } from 'app/shared/model/specialists-advice.model';

describe('Service Tests', () => {
  describe('SpecialistsAdvice Service', () => {
    let injector: TestBed;
    let service: SpecialistsAdviceService;
    let httpMock: HttpTestingController;
    let elemDefault: ISpecialistsAdvice;
    let expectedResult: ISpecialistsAdvice | ISpecialistsAdvice[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SpecialistsAdviceService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new SpecialistsAdvice(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SpecialistsAdvice', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SpecialistsAdvice()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SpecialistsAdvice', () => {
        const returnedFromService = Object.assign(
          {
            periodOfTime: 'BBBBBB',
            raisedIndicationCode: 'BBBBBB',
            raisedSubsidyPercent: 'BBBBBB',
            emphasizedIndicationCode: 'BBBBBB',
            activeSubstance: 'BBBBBB',
            efficacy: 'BBBBBB',
            dosageMod: 'BBBBBB',
            dosage: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SpecialistsAdvice', () => {
        const returnedFromService = Object.assign(
          {
            periodOfTime: 'BBBBBB',
            raisedIndicationCode: 'BBBBBB',
            raisedSubsidyPercent: 'BBBBBB',
            emphasizedIndicationCode: 'BBBBBB',
            activeSubstance: 'BBBBBB',
            efficacy: 'BBBBBB',
            dosageMod: 'BBBBBB',
            dosage: 'BBBBBB',
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

      it('should delete a SpecialistsAdvice', () => {
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
