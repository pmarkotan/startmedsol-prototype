import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { DataGeneratorDetailComponent } from 'app/entities/data-generator/data-generator-detail.component';
import { DataGenerator } from 'app/shared/model/data-generator.model';

describe('Component Tests', () => {
  describe('DataGenerator Management Detail Component', () => {
    let comp: DataGeneratorDetailComponent;
    let fixture: ComponentFixture<DataGeneratorDetailComponent>;
    const route = ({ data: of({ dataGenerator: new DataGenerator(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [DataGeneratorDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DataGeneratorDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DataGeneratorDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dataGenerator on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dataGenerator).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
