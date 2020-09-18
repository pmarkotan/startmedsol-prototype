import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDictionaryTranslation, DictionaryTranslation } from 'app/shared/model/dictionary-translation.model';
import { DictionaryTranslationService } from './dictionary-translation.service';
import { IDictionaryItem } from 'app/shared/model/dictionary-item.model';
import { DictionaryItemService } from 'app/entities/dictionary-item/dictionary-item.service';

@Component({
  selector: 'jhi-dictionary-translation-update',
  templateUrl: './dictionary-translation-update.component.html',
})
export class DictionaryTranslationUpdateComponent implements OnInit {
  isSaving = false;
  dictionaryitems: IDictionaryItem[] = [];

  editForm = this.fb.group({
    id: [],
    label: [null, [Validators.required, Validators.maxLength(32)]],
    language: [null, [Validators.required]],
    dictionaryItemId: [],
  });

  constructor(
    protected dictionaryTranslationService: DictionaryTranslationService,
    protected dictionaryItemService: DictionaryItemService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dictionaryTranslation }) => {
      this.updateForm(dictionaryTranslation);

      this.dictionaryItemService.query().subscribe((res: HttpResponse<IDictionaryItem[]>) => (this.dictionaryitems = res.body || []));
    });
  }

  updateForm(dictionaryTranslation: IDictionaryTranslation): void {
    this.editForm.patchValue({
      id: dictionaryTranslation.id,
      label: dictionaryTranslation.label,
      language: dictionaryTranslation.language,
      dictionaryItemId: dictionaryTranslation.dictionaryItemId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dictionaryTranslation = this.createFromForm();
    if (dictionaryTranslation.id !== undefined) {
      this.subscribeToSaveResponse(this.dictionaryTranslationService.update(dictionaryTranslation));
    } else {
      this.subscribeToSaveResponse(this.dictionaryTranslationService.create(dictionaryTranslation));
    }
  }

  private createFromForm(): IDictionaryTranslation {
    return {
      ...new DictionaryTranslation(),
      id: this.editForm.get(['id'])!.value,
      label: this.editForm.get(['label'])!.value,
      language: this.editForm.get(['language'])!.value,
      dictionaryItemId: this.editForm.get(['dictionaryItemId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDictionaryTranslation>>): void {
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

  trackById(index: number, item: IDictionaryItem): any {
    return item.id;
  }
}
