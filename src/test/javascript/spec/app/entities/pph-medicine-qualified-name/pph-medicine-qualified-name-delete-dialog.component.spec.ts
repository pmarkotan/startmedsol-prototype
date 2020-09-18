import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { PphMedicineQualifiedNameDeleteDialogComponent } from 'app/entities/pph-medicine-qualified-name/pph-medicine-qualified-name-delete-dialog.component';
import { PphMedicineQualifiedNameService } from 'app/entities/pph-medicine-qualified-name/pph-medicine-qualified-name.service';

describe('Component Tests', () => {
  describe('PphMedicineQualifiedName Management Delete Component', () => {
    let comp: PphMedicineQualifiedNameDeleteDialogComponent;
    let fixture: ComponentFixture<PphMedicineQualifiedNameDeleteDialogComponent>;
    let service: PphMedicineQualifiedNameService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [PphMedicineQualifiedNameDeleteDialogComponent],
      })
        .overrideTemplate(PphMedicineQualifiedNameDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PphMedicineQualifiedNameDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PphMedicineQualifiedNameService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
