import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { NavSettingsDetailComponent } from 'app/entities/nav-settings/nav-settings-detail.component';
import { NavSettings } from 'app/shared/model/nav-settings.model';

describe('Component Tests', () => {
  describe('NavSettings Management Detail Component', () => {
    let comp: NavSettingsDetailComponent;
    let fixture: ComponentFixture<NavSettingsDetailComponent>;
    const route = ({ data: of({ navSettings: new NavSettings(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [NavSettingsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NavSettingsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NavSettingsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load navSettings on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.navSettings).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
