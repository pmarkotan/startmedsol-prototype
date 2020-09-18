import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPuphaLoader, PuphaLoader } from 'app/shared/model/pupha-loader.model';
import { PuphaLoaderService } from './pupha-loader.service';

@Component({
  selector: 'jhi-pupha-loader-update',
  templateUrl: './pupha-loader-update.component.html',
})
export class PuphaLoaderUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    event: [],
    log: [],
    time: [],
  });

  constructor(protected puphaLoaderService: PuphaLoaderService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ puphaLoader }) => {
      if (!puphaLoader.id) {
        const today = moment().startOf('day');
        puphaLoader.time = today;
      }

      this.updateForm(puphaLoader);
    });
  }

  updateForm(puphaLoader: IPuphaLoader): void {
    this.editForm.patchValue({
      id: puphaLoader.id,
      event: puphaLoader.event,
      log: puphaLoader.log,
      time: puphaLoader.time ? puphaLoader.time.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const puphaLoader = this.createFromForm();
    if (puphaLoader.id !== undefined) {
      this.subscribeToSaveResponse(this.puphaLoaderService.update(puphaLoader));
    } else {
      this.subscribeToSaveResponse(this.puphaLoaderService.create(puphaLoader));
    }
  }

  private createFromForm(): IPuphaLoader {
    return {
      ...new PuphaLoader(),
      id: this.editForm.get(['id'])!.value,
      event: this.editForm.get(['event'])!.value,
      log: this.editForm.get(['log'])!.value,
      time: this.editForm.get(['time'])!.value ? moment(this.editForm.get(['time'])!.value, DATE_TIME_FORMAT) : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPuphaLoader>>): void {
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
