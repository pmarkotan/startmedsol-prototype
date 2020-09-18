import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { CodeSetLoadComponent } from 'app/entities/code-set-load/code-set-load.component';
import { CodeSetLoadService } from 'app/entities/code-set-load/code-set-load.service';
import { CodeSetLoad } from 'app/shared/model/code-set-load.model';

describe('Component Tests', () => {
  describe('CodeSetLoad Management Component', () => {
    let comp: CodeSetLoadComponent;
    let fixture: ComponentFixture<CodeSetLoadComponent>;
    let service: CodeSetLoadService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [CodeSetLoadComponent],
      })
        .overrideTemplate(CodeSetLoadComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CodeSetLoadComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CodeSetLoadService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CodeSetLoad(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.codeSetLoads && comp.codeSetLoads[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
