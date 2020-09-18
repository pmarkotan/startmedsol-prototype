import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IHashTag, HashTag } from 'app/shared/model/hash-tag.model';
import { HashTagService } from './hash-tag.service';
import { IProvider } from 'app/shared/model/provider.model';
import { ProviderService } from 'app/entities/provider/provider.service';

@Component({
  selector: 'jhi-hash-tag-update',
  templateUrl: './hash-tag-update.component.html',
})
export class HashTagUpdateComponent implements OnInit {
  isSaving = false;
  providers: IProvider[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(256)]],
    providerId: [null, Validators.required],
  });

  constructor(
    protected hashTagService: HashTagService,
    protected providerService: ProviderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hashTag }) => {
      this.updateForm(hashTag);

      this.providerService.query().subscribe((res: HttpResponse<IProvider[]>) => (this.providers = res.body || []));
    });
  }

  updateForm(hashTag: IHashTag): void {
    this.editForm.patchValue({
      id: hashTag.id,
      name: hashTag.name,
      providerId: hashTag.providerId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const hashTag = this.createFromForm();
    if (hashTag.id !== undefined) {
      this.subscribeToSaveResponse(this.hashTagService.update(hashTag));
    } else {
      this.subscribeToSaveResponse(this.hashTagService.create(hashTag));
    }
  }

  private createFromForm(): IHashTag {
    return {
      ...new HashTag(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      providerId: this.editForm.get(['providerId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHashTag>>): void {
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

  trackById(index: number, item: IProvider): any {
    return item.id;
  }
}
