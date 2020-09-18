import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { ContactPersonDetailComponent } from 'app/entities/contact-person/contact-person-detail.component';
import { ContactPerson } from 'app/shared/model/contact-person.model';

describe('Component Tests', () => {
  describe('ContactPerson Management Detail Component', () => {
    let comp: ContactPersonDetailComponent;
    let fixture: ComponentFixture<ContactPersonDetailComponent>;
    const route = ({ data: of({ contactPerson: new ContactPerson(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [ContactPersonDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ContactPersonDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ContactPersonDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load contactPerson on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.contactPerson).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
