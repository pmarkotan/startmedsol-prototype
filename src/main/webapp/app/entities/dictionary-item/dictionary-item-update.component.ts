import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDictionaryItem, DictionaryItem } from 'app/shared/model/dictionary-item.model';
import { DictionaryItemService } from './dictionary-item.service';

@Component({
  selector: 'jhi-dictionary-item-update',
  templateUrl: './dictionary-item-update.component.html',
})
export class DictionaryItemUpdateComponent implements OnInit {
  isSaving = false;
  dictionaryitems: IDictionaryItem[] = [];

  editForm = this.fb.group({
    id: [],
    dictionaryItemType: [null, [Validators.required, Validators.maxLength(32)]],
    code: [null, [Validators.required, Validators.maxLength(64)]],
    orderNumber: [],
    description: [null, [Validators.maxLength(255)]],
    parents: [],
  });

  constructor(protected dictionaryItemService: DictionaryItemService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dictionaryItem }) => {
      this.updateForm(dictionaryItem);

      this.dictionaryItemService.query().subscribe((res: HttpResponse<IDictionaryItem[]>) => (this.dictionaryitems = res.body || []));
    });
  }

  updateForm(dictionaryItem: IDictionaryItem): void {
    this.editForm.patchValue({
      id: dictionaryItem.id,
      dictionaryItemType: dictionaryItem.dictionaryItemType,
      code: dictionaryItem.code,
      orderNumber: dictionaryItem.orderNumber,
      description: dictionaryItem.description,
      parents: dictionaryItem.parents,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dictionaryItem = this.createFromForm();
    if (dictionaryItem.id !== undefined) {
      this.subscribeToSaveResponse(this.dictionaryItemService.update(dictionaryItem));
    } else {
      this.subscribeToSaveResponse(this.dictionaryItemService.create(dictionaryItem));
    }
  }

  private createFromForm(): IDictionaryItem {
    return {
      ...new DictionaryItem(),
      id: this.editForm.get(['id'])!.value,
      dictionaryItemType: this.editForm.get(['dictionaryItemType'])!.value,
      code: this.editForm.get(['code'])!.value,
      orderNumber: this.editForm.get(['orderNumber'])!.value,
      description: this.editForm.get(['description'])!.value,
      parents: this.editForm.get(['parents'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDictionaryItem>>): void {
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

  getSelected(selectedVals: IDictionaryItem[], option: IDictionaryItem): IDictionaryItem {
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
