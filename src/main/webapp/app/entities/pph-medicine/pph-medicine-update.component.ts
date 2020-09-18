import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPphMedicine, PphMedicine } from 'app/shared/model/pph-medicine.model';
import { PphMedicineService } from './pph-medicine.service';
import { IPphCompany } from 'app/shared/model/pph-company.model';
import { PphCompanyService } from 'app/entities/pph-company/pph-company.service';
import { IPphMedicineForm } from 'app/shared/model/pph-medicine-form.model';
import { PphMedicineFormService } from 'app/entities/pph-medicine-form/pph-medicine-form.service';
import { IPphMotivationGroup } from 'app/shared/model/pph-motivation-group.model';
import { PphMotivationGroupService } from 'app/entities/pph-motivation-group/pph-motivation-group.service';
import { IPphNiche } from 'app/shared/model/pph-niche.model';
import { PphNicheService } from 'app/entities/pph-niche/pph-niche.service';
import { IPphEuScore } from 'app/shared/model/pph-eu-score.model';
import { PphEuScoreService } from 'app/entities/pph-eu-score/pph-eu-score.service';
import { IPphMedicineQualifiedName } from 'app/shared/model/pph-medicine-qualified-name.model';
import { PphMedicineQualifiedNameService } from 'app/entities/pph-medicine-qualified-name/pph-medicine-qualified-name.service';

type SelectableEntity = IPphCompany | IPphMedicineForm | IPphMotivationGroup | IPphNiche | IPphEuScore | IPphMedicineQualifiedName;

@Component({
  selector: 'jhi-pph-medicine-update',
  templateUrl: './pph-medicine-update.component.html',
})
export class PphMedicineUpdateComponent implements OnInit {
  isSaving = false;
  pphcompanies: IPphCompany[] = [];
  pphmedicineforms: IPphMedicineForm[] = [];
  pphmotivationgroups: IPphMotivationGroup[] = [];
  pphniches: IPphNiche[] = [];
  ppheuscores: IPphEuScore[] = [];
  pphmedicinequalifiednames: IPphMedicineQualifiedName[] = [];

  editForm = this.fb.group({
    id: [],
    productId: [],
    masterBookId: [null, [Validators.maxLength(64)]],
    name: [null, [Validators.maxLength(256)]],
    packageDescription: [null, [Validators.maxLength(256)]],
    eanCode: [null, [Validators.maxLength(16)]],
    atcCode: [null, [Validators.maxLength(8)]],
    brand: [null, [Validators.maxLength(128)]],
    activeSubstance: [null, [Validators.maxLength(128)]],
    potency: [null, [Validators.maxLength(64)]],
    substanceAmountTotal: [],
    doseInPackage: [],
    substanceAmount: [],
    doseMsmUnit: [null, [Validators.maxLength(32)]],
    substanceMsmUnit: [null, [Validators.maxLength(50)]],
    packageSize: [],
    packageMsmUnit: [null, [Validators.maxLength(50)]],
    dailyDoze: [],
    dailyDozeMsmUnit: [null, [Validators.maxLength(50)]],
    ddMsmUnitFactor: [],
    daysOfTherapy: [],
    consumerPrice: [],
    grossConsumerPrice: [],
    efficacy: [null, [Validators.maxLength(32)]],
    oldName: [null, [Validators.maxLength(256)]],
    oepTtt: [null, [Validators.maxLength(9)]],
    masterBookDeleted: [],
    mbookDeletedDate: [],
    available: [],
    motivationStatus: [],
    costEffectivityCode: [],
    dailyTherapyCost: [],
    equivalenceGroupId: [],
    oldSubsidyType: [null, [Validators.maxLength(4)]],
    preferredPriceFlag: [],
    pharmacyFlag: [null, [Validators.maxLength(2)]],
    customMade: [],
    medicalDeviceIso: [null, [Validators.maxLength(16)]],
    producerPrice: [],
    wholesalePrice: [],
    vatRate: [],
    armyPrescription: [],
    publicSupply: [],
    workAccidentPrescr: [],
    extraSubsidyPrescr: [],
    medicalSubsidyPrescr: [],
    substancePrice: [],
    includeInHc2: [],
    prescriptionType: [],
    medicineType: [],
    medicineStatus: [],
    normative: [],
    ogyiId: [],
    normFixGroupId: [],
    extraSubsidyFixGroupId: [],
    medicalSubsidyPrescriptionFixGroupId: [],
    dosageMod: [null, [Validators.maxLength(256)]],
    activePuphaData: [null, [Validators.required]],
    dealerIdId: [],
    marketingAuthOwnerId: [],
    medicineFormId: [],
    motivationGroupId: [],
    nicheId: [],
    euScores: [],
    qualifiedNameId: [],
  });

  constructor(
    protected pphMedicineService: PphMedicineService,
    protected pphCompanyService: PphCompanyService,
    protected pphMedicineFormService: PphMedicineFormService,
    protected pphMotivationGroupService: PphMotivationGroupService,
    protected pphNicheService: PphNicheService,
    protected pphEuScoreService: PphEuScoreService,
    protected pphMedicineQualifiedNameService: PphMedicineQualifiedNameService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pphMedicine }) => {
      if (!pphMedicine.id) {
        const today = moment().startOf('day');
        pphMedicine.mbookDeletedDate = today;
      }

      this.updateForm(pphMedicine);

      this.pphCompanyService.query().subscribe((res: HttpResponse<IPphCompany[]>) => (this.pphcompanies = res.body || []));

      this.pphMedicineFormService.query().subscribe((res: HttpResponse<IPphMedicineForm[]>) => (this.pphmedicineforms = res.body || []));

      this.pphMotivationGroupService
        .query()
        .subscribe((res: HttpResponse<IPphMotivationGroup[]>) => (this.pphmotivationgroups = res.body || []));

      this.pphNicheService.query().subscribe((res: HttpResponse<IPphNiche[]>) => (this.pphniches = res.body || []));

      this.pphEuScoreService.query().subscribe((res: HttpResponse<IPphEuScore[]>) => (this.ppheuscores = res.body || []));

      this.pphMedicineQualifiedNameService
        .query()
        .subscribe((res: HttpResponse<IPphMedicineQualifiedName[]>) => (this.pphmedicinequalifiednames = res.body || []));
    });
  }

  updateForm(pphMedicine: IPphMedicine): void {
    this.editForm.patchValue({
      id: pphMedicine.id,
      productId: pphMedicine.productId,
      masterBookId: pphMedicine.masterBookId,
      name: pphMedicine.name,
      packageDescription: pphMedicine.packageDescription,
      eanCode: pphMedicine.eanCode,
      atcCode: pphMedicine.atcCode,
      brand: pphMedicine.brand,
      activeSubstance: pphMedicine.activeSubstance,
      potency: pphMedicine.potency,
      substanceAmountTotal: pphMedicine.substanceAmountTotal,
      doseInPackage: pphMedicine.doseInPackage,
      substanceAmount: pphMedicine.substanceAmount,
      doseMsmUnit: pphMedicine.doseMsmUnit,
      substanceMsmUnit: pphMedicine.substanceMsmUnit,
      packageSize: pphMedicine.packageSize,
      packageMsmUnit: pphMedicine.packageMsmUnit,
      dailyDoze: pphMedicine.dailyDoze,
      dailyDozeMsmUnit: pphMedicine.dailyDozeMsmUnit,
      ddMsmUnitFactor: pphMedicine.ddMsmUnitFactor,
      daysOfTherapy: pphMedicine.daysOfTherapy,
      consumerPrice: pphMedicine.consumerPrice,
      grossConsumerPrice: pphMedicine.grossConsumerPrice,
      efficacy: pphMedicine.efficacy,
      oldName: pphMedicine.oldName,
      oepTtt: pphMedicine.oepTtt,
      masterBookDeleted: pphMedicine.masterBookDeleted,
      mbookDeletedDate: pphMedicine.mbookDeletedDate ? pphMedicine.mbookDeletedDate.format(DATE_TIME_FORMAT) : null,
      available: pphMedicine.available,
      motivationStatus: pphMedicine.motivationStatus,
      costEffectivityCode: pphMedicine.costEffectivityCode,
      dailyTherapyCost: pphMedicine.dailyTherapyCost,
      equivalenceGroupId: pphMedicine.equivalenceGroupId,
      oldSubsidyType: pphMedicine.oldSubsidyType,
      preferredPriceFlag: pphMedicine.preferredPriceFlag,
      pharmacyFlag: pphMedicine.pharmacyFlag,
      customMade: pphMedicine.customMade,
      medicalDeviceIso: pphMedicine.medicalDeviceIso,
      producerPrice: pphMedicine.producerPrice,
      wholesalePrice: pphMedicine.wholesalePrice,
      vatRate: pphMedicine.vatRate,
      armyPrescription: pphMedicine.armyPrescription,
      publicSupply: pphMedicine.publicSupply,
      workAccidentPrescr: pphMedicine.workAccidentPrescr,
      extraSubsidyPrescr: pphMedicine.extraSubsidyPrescr,
      medicalSubsidyPrescr: pphMedicine.medicalSubsidyPrescr,
      substancePrice: pphMedicine.substancePrice,
      includeInHc2: pphMedicine.includeInHc2,
      prescriptionType: pphMedicine.prescriptionType,
      medicineType: pphMedicine.medicineType,
      medicineStatus: pphMedicine.medicineStatus,
      normative: pphMedicine.normative,
      ogyiId: pphMedicine.ogyiId,
      normFixGroupId: pphMedicine.normFixGroupId,
      extraSubsidyFixGroupId: pphMedicine.extraSubsidyFixGroupId,
      medicalSubsidyPrescriptionFixGroupId: pphMedicine.medicalSubsidyPrescriptionFixGroupId,
      dosageMod: pphMedicine.dosageMod,
      activePuphaData: pphMedicine.activePuphaData,
      dealerIdId: pphMedicine.dealerIdId,
      marketingAuthOwnerId: pphMedicine.marketingAuthOwnerId,
      medicineFormId: pphMedicine.medicineFormId,
      motivationGroupId: pphMedicine.motivationGroupId,
      nicheId: pphMedicine.nicheId,
      euScores: pphMedicine.euScores,
      qualifiedNameId: pphMedicine.qualifiedNameId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const pphMedicine = this.createFromForm();
    if (pphMedicine.id !== undefined) {
      this.subscribeToSaveResponse(this.pphMedicineService.update(pphMedicine));
    } else {
      this.subscribeToSaveResponse(this.pphMedicineService.create(pphMedicine));
    }
  }

  private createFromForm(): IPphMedicine {
    return {
      ...new PphMedicine(),
      id: this.editForm.get(['id'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      masterBookId: this.editForm.get(['masterBookId'])!.value,
      name: this.editForm.get(['name'])!.value,
      packageDescription: this.editForm.get(['packageDescription'])!.value,
      eanCode: this.editForm.get(['eanCode'])!.value,
      atcCode: this.editForm.get(['atcCode'])!.value,
      brand: this.editForm.get(['brand'])!.value,
      activeSubstance: this.editForm.get(['activeSubstance'])!.value,
      potency: this.editForm.get(['potency'])!.value,
      substanceAmountTotal: this.editForm.get(['substanceAmountTotal'])!.value,
      doseInPackage: this.editForm.get(['doseInPackage'])!.value,
      substanceAmount: this.editForm.get(['substanceAmount'])!.value,
      doseMsmUnit: this.editForm.get(['doseMsmUnit'])!.value,
      substanceMsmUnit: this.editForm.get(['substanceMsmUnit'])!.value,
      packageSize: this.editForm.get(['packageSize'])!.value,
      packageMsmUnit: this.editForm.get(['packageMsmUnit'])!.value,
      dailyDoze: this.editForm.get(['dailyDoze'])!.value,
      dailyDozeMsmUnit: this.editForm.get(['dailyDozeMsmUnit'])!.value,
      ddMsmUnitFactor: this.editForm.get(['ddMsmUnitFactor'])!.value,
      daysOfTherapy: this.editForm.get(['daysOfTherapy'])!.value,
      consumerPrice: this.editForm.get(['consumerPrice'])!.value,
      grossConsumerPrice: this.editForm.get(['grossConsumerPrice'])!.value,
      efficacy: this.editForm.get(['efficacy'])!.value,
      oldName: this.editForm.get(['oldName'])!.value,
      oepTtt: this.editForm.get(['oepTtt'])!.value,
      masterBookDeleted: this.editForm.get(['masterBookDeleted'])!.value,
      mbookDeletedDate: this.editForm.get(['mbookDeletedDate'])!.value
        ? moment(this.editForm.get(['mbookDeletedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      available: this.editForm.get(['available'])!.value,
      motivationStatus: this.editForm.get(['motivationStatus'])!.value,
      costEffectivityCode: this.editForm.get(['costEffectivityCode'])!.value,
      dailyTherapyCost: this.editForm.get(['dailyTherapyCost'])!.value,
      equivalenceGroupId: this.editForm.get(['equivalenceGroupId'])!.value,
      oldSubsidyType: this.editForm.get(['oldSubsidyType'])!.value,
      preferredPriceFlag: this.editForm.get(['preferredPriceFlag'])!.value,
      pharmacyFlag: this.editForm.get(['pharmacyFlag'])!.value,
      customMade: this.editForm.get(['customMade'])!.value,
      medicalDeviceIso: this.editForm.get(['medicalDeviceIso'])!.value,
      producerPrice: this.editForm.get(['producerPrice'])!.value,
      wholesalePrice: this.editForm.get(['wholesalePrice'])!.value,
      vatRate: this.editForm.get(['vatRate'])!.value,
      armyPrescription: this.editForm.get(['armyPrescription'])!.value,
      publicSupply: this.editForm.get(['publicSupply'])!.value,
      workAccidentPrescr: this.editForm.get(['workAccidentPrescr'])!.value,
      extraSubsidyPrescr: this.editForm.get(['extraSubsidyPrescr'])!.value,
      medicalSubsidyPrescr: this.editForm.get(['medicalSubsidyPrescr'])!.value,
      substancePrice: this.editForm.get(['substancePrice'])!.value,
      includeInHc2: this.editForm.get(['includeInHc2'])!.value,
      prescriptionType: this.editForm.get(['prescriptionType'])!.value,
      medicineType: this.editForm.get(['medicineType'])!.value,
      medicineStatus: this.editForm.get(['medicineStatus'])!.value,
      normative: this.editForm.get(['normative'])!.value,
      ogyiId: this.editForm.get(['ogyiId'])!.value,
      normFixGroupId: this.editForm.get(['normFixGroupId'])!.value,
      extraSubsidyFixGroupId: this.editForm.get(['extraSubsidyFixGroupId'])!.value,
      medicalSubsidyPrescriptionFixGroupId: this.editForm.get(['medicalSubsidyPrescriptionFixGroupId'])!.value,
      dosageMod: this.editForm.get(['dosageMod'])!.value,
      activePuphaData: this.editForm.get(['activePuphaData'])!.value,
      dealerIdId: this.editForm.get(['dealerIdId'])!.value,
      marketingAuthOwnerId: this.editForm.get(['marketingAuthOwnerId'])!.value,
      medicineFormId: this.editForm.get(['medicineFormId'])!.value,
      motivationGroupId: this.editForm.get(['motivationGroupId'])!.value,
      nicheId: this.editForm.get(['nicheId'])!.value,
      euScores: this.editForm.get(['euScores'])!.value,
      qualifiedNameId: this.editForm.get(['qualifiedNameId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPphMedicine>>): void {
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

  getSelected(selectedVals: IPphEuScore[], option: IPphEuScore): IPphEuScore {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
