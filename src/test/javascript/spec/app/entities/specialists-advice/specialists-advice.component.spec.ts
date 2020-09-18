import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { SpecialistsAdviceComponent } from 'app/entities/specialists-advice/specialists-advice.component';
import { SpecialistsAdviceService } from 'app/entities/specialists-advice/specialists-advice.service';
import { SpecialistsAdvice } from 'app/shared/model/specialists-advice.model';

describe('Component Tests', () => {
  describe('SpecialistsAdvice Management Component', () => {
    let comp: SpecialistsAdviceComponent;
    let fixture: ComponentFixture<SpecialistsAdviceComponent>;
    let service: SpecialistsAdviceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [SpecialistsAdviceComponent],
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
        .overrideTemplate(SpecialistsAdviceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SpecialistsAdviceComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SpecialistsAdviceService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SpecialistsAdvice(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.specialistsAdvices && comp.specialistsAdvices[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SpecialistsAdvice(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.specialistsAdvices && comp.specialistsAdvices[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
