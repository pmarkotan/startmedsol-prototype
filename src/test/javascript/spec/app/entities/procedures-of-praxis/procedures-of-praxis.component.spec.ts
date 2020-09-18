import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { ProceduresOfPraxisComponent } from 'app/entities/procedures-of-praxis/procedures-of-praxis.component';
import { ProceduresOfPraxisService } from 'app/entities/procedures-of-praxis/procedures-of-praxis.service';
import { ProceduresOfPraxis } from 'app/shared/model/procedures-of-praxis.model';

describe('Component Tests', () => {
  describe('ProceduresOfPraxis Management Component', () => {
    let comp: ProceduresOfPraxisComponent;
    let fixture: ComponentFixture<ProceduresOfPraxisComponent>;
    let service: ProceduresOfPraxisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [ProceduresOfPraxisComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(ProceduresOfPraxisComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProceduresOfPraxisComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProceduresOfPraxisService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProceduresOfPraxis(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.proceduresOfPraxes && comp.proceduresOfPraxes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProceduresOfPraxis(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.proceduresOfPraxes && comp.proceduresOfPraxes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
