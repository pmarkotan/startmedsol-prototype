import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICodeSetLoad, CodeSetLoad } from 'app/shared/model/code-set-load.model';
import { CodeSetLoadService } from './code-set-load.service';

@Component({
  selector: 'jhi-code-set-load-update',
  templateUrl: './code-set-load-update.component.html',
})
export class CodeSetLoadUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    type: [],
    latestVersion: [],
    url: [],
    log: [],
    status: [],
  });

  constructor(protected codeSetLoadService: CodeSetLoadService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ codeSetLoad }) => {
      this.updateForm(codeSetLoad);
    });
  }

  updateForm(codeSetLoad: ICodeSetLoad): void {
    this.editForm.patchValue({
      id: codeSetLoad.id,
      type: codeSetLoad.type,
      latestVersion: codeSetLoad.latestVersion,
      url: codeSetLoad.url,
      log: codeSetLoad.log,
      status: codeSetLoad.status,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const codeSetLoad = this.createFromForm();
    if (codeSetLoad.id !== undefined) {
      this.subscribeToSaveResponse(this.codeSetLoadService.update(codeSetLoad));
    } else {
      this.subscribeToSaveResponse(this.codeSetLoadService.create(codeSetLoad));
    }
  }

  private createFromForm(): ICodeSetLoad {
    return {
      ...new CodeSetLoad(),
      id: this.editForm.get(['id'])!.value,
      type: this.editForm.get(['type'])!.value,
      latestVersion: this.editForm.get(['latestVersion'])!.value,
      url: this.editForm.get(['url'])!.value,
      log: this.editForm.get(['log'])!.value,
      status: this.editForm.get(['status'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICodeSetLoad>>): void {
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
