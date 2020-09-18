import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { StartMedsolPrototypeTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { MedicalCaseDiagnosisDeleteDialogComponent } from 'app/entities/medical-case-diagnosis/medical-case-diagnosis-delete-dialog.component';
import { MedicalCaseDiagnosisService } from 'app/entities/medical-case-diagnosis/medical-case-diagnosis.service';

describe('Component Tests', () => {
  describe('MedicalCaseDiagnosis Management Delete Component', () => {
    let comp: MedicalCaseDiagnosisDeleteDialogComponent;
    let fixture: ComponentFixture<MedicalCaseDiagnosisDeleteDialogComponent>;
    let service: MedicalCaseDiagnosisService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StartMedsolPrototypeTestModule],
        declarations: [MedicalCaseDiagnosisDeleteDialogComponent],
      })
        .overrideTemplate(MedicalCaseDiagnosisDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MedicalCaseDiagnosisDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedicalCaseDiagnosisService);
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
