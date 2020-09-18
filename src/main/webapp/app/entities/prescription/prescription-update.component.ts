import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPrescription, Prescription } from 'app/shared/model/prescription.model';
import { PrescriptionService } from './prescription.service';
import { ICsDiagnosis } from 'app/shared/model/cs-diagnosis.model';
import { CsDiagnosisService } from 'app/entities/cs-diagnosis/cs-diagnosis.service';
import { IEmployee } from 'app/shared/model/employee.model';
import { EmployeeService } from 'app/entities/employee/employee.service';
import { IPphMedicine } from 'app/shared/model/pph-medicine.model';
import { PphMedicineService } from 'app/entities/pph-medicine/pph-medicine.service';
import { IMedicalCase } from 'app/shared/model/medical-case.model';
import { MedicalCaseService } from 'app/entities/medical-case/medical-case.service';
import { IPphPuphaVersion } from 'app/shared/model/pph-pupha-version.model';
import { PphPuphaVersionService } from 'app/entities/pph-pupha-version/pph-pupha-version.service';

type SelectableEntity = ICsDiagnosis | IEmployee | IPphMedicine | IMedicalCase | IPphPuphaVersion;

@Component({
  selector: 'jhi-prescription-update',
  templateUrl: './prescription-update.component.html',
})
export class PrescriptionUpdateComponent implements OnInit {
  isSaving = false;
  csdiagnoses: ICsDiagnosis[] = [];
  employees: IEmployee[] = [];
  pphmedicines: IPphMedicine[] = [];
  medicalcases: IMedicalCase[] = [];
  pphpuphaversions: IPphPuphaVersion[] = [];

  editForm = this.fb.group({
    id: [],
    openedPackage: [],
    medicalAidTeachPrescribing: [],
    forH2cCertificate: [],
    inscriptionDate: [null, [Validators.required]],
    causeOfNotReplaceability: [null, [Validators.maxLength(50)]],
    description: [null, [Validators.maxLength(1024)]],
    doseMsmUnit: [null, [Validators.maxLength(32)]],
    eesztGroupId: [null, [Validators.maxLength(36)]],
    institution: [null, [Validators.maxLength(1024)]],
    instructions: [null, [Validators.maxLength(255)]],
    integrationId: [null, [Validators.maxLength(50)]],
    equippedWith: [],
    acceptedQualificationRule: [null, [Validators.required]],
    subsidyCategory: [],
    status: [null, [Validators.required]],
    eesztSendingStatus: [],
    medicalProductType: [null, [Validators.required]],
    preparationDescription: [null, [Validators.maxLength(50)]],
    invalidationReason: [null, [Validators.maxLength(255)]],
    frequency: [null, [Validators.required]],
    frequencyMultiplier: [],
    quantityMultiplier: [],
    morning: [],
    noon: [],
    evening: [],
    beforeSleep: [],
    dosagePatternText: [null, [Validators.maxLength(100)]],
    quantity: [],
    quantityCause: [null, [Validators.maxLength(50)]],
    forSeveralMonths: [null, [Validators.required]],
    monthsSuppliedFor: [],
    forOnePrescription: [],
    specialistRegNo: [null, [Validators.maxLength(16)]],
    proposalDate: [],
    diagnosisId: [null, Validators.required],
    inscriberDoctorId: [null, Validators.required],
    qualificationRuleAcceptorId: [null, Validators.required],
    medicineId: [null, Validators.required],
    medicalCaseId: [null, Validators.required],
    puphaVersionId: [null, Validators.required],
  });

  constructor(
    protected prescriptionService: PrescriptionService,
    protected csDiagnosisService: CsDiagnosisService,
    protected employeeService: EmployeeService,
    protected pphMedicineService: PphMedicineService,
    protected medicalCaseService: MedicalCaseService,
    protected pphPuphaVersionService: PphPuphaVersionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ prescription }) => {
      if (!prescription.id) {
        const today = moment().startOf('day');
        prescription.inscriptionDate = today;
        prescription.proposalDate = today;
      }

      this.updateForm(prescription);

      this.csDiagnosisService.query().subscribe((res: HttpResponse<ICsDiagnosis[]>) => (this.csdiagnoses = res.body || []));

      this.employeeService.query().subscribe((res: HttpResponse<IEmployee[]>) => (this.employees = res.body || []));

      this.pphMedicineService.query().subscribe((res: HttpResponse<IPphMedicine[]>) => (this.pphmedicines = res.body || []));

      this.medicalCaseService.query().subscribe((res: HttpResponse<IMedicalCase[]>) => (this.medicalcases = res.body || []));

      this.pphPuphaVersionService.query().subscribe((res: HttpResponse<IPphPuphaVersion[]>) => (this.pphpuphaversions = res.body || []));
    });
  }

  updateForm(prescription: IPrescription): void {
    this.editForm.patchValue({
      id: prescription.id,
      openedPackage: prescription.openedPackage,
      medicalAidTeachPrescribing: prescription.medicalAidTeachPrescribing,
      forH2cCertificate: prescription.forH2cCertificate,
      inscriptionDate: prescription.inscriptionDate ? prescription.inscriptionDate.format(DATE_TIME_FORMAT) : null,
      causeOfNotReplaceability: prescription.causeOfNotReplaceability,
      description: prescription.description,
      doseMsmUnit: prescription.doseMsmUnit,
      eesztGroupId: prescription.eesztGroupId,
      institution: prescription.institution,
      instructions: prescription.instructions,
      integrationId: prescription.integrationId,
      equippedWith: prescription.equippedWith,
      acceptedQualificationRule: prescription.acceptedQualificationRule,
      subsidyCategory: prescription.subsidyCategory,
      status: prescription.status,
      eesztSendingStatus: prescription.eesztSendingStatus,
      medicalProductType: prescription.medicalProductType,
      preparationDescription: prescription.preparationDescription,
      invalidationReason: prescription.invalidationReason,
      frequency: prescription.frequency,
      frequencyMultiplier: prescription.frequencyMultiplier,
      quantityMultiplier: prescription.quantityMultiplier,
      morning: prescription.morning,
      noon: prescription.noon,
      evening: prescription.evening,
      beforeSleep: prescription.beforeSleep,
      dosagePatternText: prescription.dosagePatternText,
      quantity: prescription.quantity,
      quantityCause: prescription.quantityCause,
      forSeveralMonths: prescription.forSeveralMonths,
      monthsSuppliedFor: prescription.monthsSuppliedFor,
      forOnePrescription: prescription.forOnePrescription,
      specialistRegNo: prescription.specialistRegNo,
      proposalDate: prescription.proposalDate ? prescription.proposalDate.format(DATE_TIME_FORMAT) : null,
      diagnosisId: prescription.diagnosisId,
      inscriberDoctorId: prescription.inscriberDoctorId,
      qualificationRuleAcceptorId: prescription.qualificationRuleAcceptorId,
      medicineId: prescription.medicineId,
      medicalCaseId: prescription.medicalCaseId,
      puphaVersionId: prescription.puphaVersionId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const prescription = this.createFromForm();
    if (prescription.id !== undefined) {
      this.subscribeToSaveResponse(this.prescriptionService.update(prescription));
    } else {
      this.subscribeToSaveResponse(this.prescriptionService.create(prescription));
    }
  }

  private createFromForm(): IPrescription {
    return {
      ...new Prescription(),
      id: this.editForm.get(['id'])!.value,
      openedPackage: this.editForm.get(['openedPackage'])!.value,
      medicalAidTeachPrescribing: this.editForm.get(['medicalAidTeachPrescribing'])!.value,
      forH2cCertificate: this.editForm.get(['forH2cCertificate'])!.value,
      inscriptionDate: this.editForm.get(['inscriptionDate'])!.value
        ? moment(this.editForm.get(['inscriptionDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      causeOfNotReplaceability: this.editForm.get(['causeOfNotReplaceability'])!.value,
      description: this.editForm.get(['description'])!.value,
      doseMsmUnit: this.editForm.get(['doseMsmUnit'])!.value,
      eesztGroupId: this.editForm.get(['eesztGroupId'])!.value,
      institution: this.editForm.get(['institution'])!.value,
      instructions: this.editForm.get(['instructions'])!.value,
      integrationId: this.editForm.get(['integrationId'])!.value,
      equippedWith: this.editForm.get(['equippedWith'])!.value,
      acceptedQualificationRule: this.editForm.get(['acceptedQualificationRule'])!.value,
      subsidyCategory: this.editForm.get(['subsidyCategory'])!.value,
      status: this.editForm.get(['status'])!.value,
      eesztSendingStatus: this.editForm.get(['eesztSendingStatus'])!.value,
      medicalProductType: this.editForm.get(['medicalProductType'])!.value,
      preparationDescription: this.editForm.get(['preparationDescription'])!.value,
      invalidationReason: this.editForm.get(['invalidationReason'])!.value,
      frequency: this.editForm.get(['frequency'])!.value,
      frequencyMultiplier: this.editForm.get(['frequencyMultiplier'])!.value,
      quantityMultiplier: this.editForm.get(['quantityMultiplier'])!.value,
      morning: this.editForm.get(['morning'])!.value,
      noon: this.editForm.get(['noon'])!.value,
      evening: this.editForm.get(['evening'])!.value,
      beforeSleep: this.editForm.get(['beforeSleep'])!.value,
      dosagePatternText: this.editForm.get(['dosagePatternText'])!.value,
      quantity: this.editForm.get(['quantity'])!.value,
      quantityCause: this.editForm.get(['quantityCause'])!.value,
      forSeveralMonths: this.editForm.get(['forSeveralMonths'])!.value,
      monthsSuppliedFor: this.editForm.get(['monthsSuppliedFor'])!.value,
      forOnePrescription: this.editForm.get(['forOnePrescription'])!.value,
      specialistRegNo: this.editForm.get(['specialistRegNo'])!.value,
      proposalDate: this.editForm.get(['proposalDate'])!.value
        ? moment(this.editForm.get(['proposalDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      diagnosisId: this.editForm.get(['diagnosisId'])!.value,
      inscriberDoctorId: this.editForm.get(['inscriberDoctorId'])!.value,
      qualificationRuleAcceptorId: this.editForm.get(['qualificationRuleAcceptorId'])!.value,
      medicineId: this.editForm.get(['medicineId'])!.value,
      medicalCaseId: this.editForm.get(['medicalCaseId'])!.value,
      puphaVersionId: this.editForm.get(['puphaVersionId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrescription>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
