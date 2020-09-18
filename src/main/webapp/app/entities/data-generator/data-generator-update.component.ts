import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDataGenerator, DataGenerator } from 'app/shared/model/data-generator.model';
import { DataGeneratorService } from './data-generator.service';

@Component({
  selector: 'jhi-data-generator-update',
  templateUrl: './data-generator-update.component.html',
})
export class DataGeneratorUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    provider: [],
    praxis: [],
    patient: [],
    medicalCase: [],
  });

  constructor(protected dataGeneratorService: DataGeneratorService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dataGenerator }) => {
      this.updateForm(dataGenerator);
    });
  }

  updateForm(dataGenerator: IDataGenerator): void {
    this.editForm.patchValue({
      id: dataGenerator.id,
      provider: dataGenerator.provider,
      praxis: dataGenerator.praxis,
      patient: dataGenerator.patient,
      medicalCase: dataGenerator.medicalCase,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dataGenerator = this.createFromForm();
    if (dataGenerator.id !== undefined) {
      this.subscribeToSaveResponse(this.dataGeneratorService.update(dataGenerator));
    } else {
      this.subscribeToSaveResponse(this.dataGeneratorService.create(dataGenerator));
    }
  }

  private createFromForm(): IDataGenerator {
    return {
      ...new DataGenerator(),
      id: this.editForm.get(['id'])!.value,
      provider: this.editForm.get(['provider'])!.value,
      praxis: this.editForm.get(['praxis'])!.value,
      patient: this.editForm.get(['patient'])!.value,
      medicalCase: this.editForm.get(['medicalCase'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDataGenerator>>): void {
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
}
