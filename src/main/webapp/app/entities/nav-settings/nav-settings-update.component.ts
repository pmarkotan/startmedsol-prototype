import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INavSettings, NavSettings } from 'app/shared/model/nav-settings.model';
import { NavSettingsService } from './nav-settings.service';

@Component({
  selector: 'jhi-nav-settings-update',
  templateUrl: './nav-settings-update.component.html',
})
export class NavSettingsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    technicalUserName: [null, [Validators.required, Validators.maxLength(100)]],
    technicalPassword: [null, [Validators.required, Validators.maxLength(100)]],
    signingKey: [null, [Validators.required, Validators.maxLength(100)]],
    replacementKey: [null, [Validators.required, Validators.maxLength(100)]],
  });

  constructor(protected navSettingsService: NavSettingsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ navSettings }) => {
      this.updateForm(navSettings);
    });
  }

  updateForm(navSettings: INavSettings): void {
    this.editForm.patchValue({
      id: navSettings.id,
      technicalUserName: navSettings.technicalUserName,
      technicalPassword: navSettings.technicalPassword,
      signingKey: navSettings.signingKey,
      replacementKey: navSettings.replacementKey,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const navSettings = this.createFromForm();
    if (navSettings.id !== undefined) {
      this.subscribeToSaveResponse(this.navSettingsService.update(navSettings));
    } else {
      this.subscribeToSaveResponse(this.navSettingsService.create(navSettings));
    }
  }

  private createFromForm(): INavSettings {
    return {
      ...new NavSettings(),
      id: this.editForm.get(['id'])!.value,
      technicalUserName: this.editForm.get(['technicalUserName'])!.value,
      technicalPassword: this.editForm.get(['technicalPassword'])!.value,
      signingKey: this.editForm.get(['signingKey'])!.value,
      replacementKey: this.editForm.get(['replacementKey'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INavSettings>>): void {
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
