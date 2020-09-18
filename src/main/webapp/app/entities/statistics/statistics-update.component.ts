import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IStatistics, Statistics } from 'app/shared/model/statistics.model';
import { StatisticsService } from './statistics.service';

@Component({
  selector: 'jhi-statistics-update',
  templateUrl: './statistics-update.component.html',
})
export class StatisticsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    created: [null, [Validators.required]],
    tpye: [null, [Validators.required]],
    content: [null, [Validators.maxLength(1024)]],
    description: [null, [Validators.maxLength(1024)]],
  });

  constructor(protected statisticsService: StatisticsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ statistics }) => {
      if (!statistics.id) {
        const today = moment().startOf('day');
        statistics.created = today;
      }

      this.updateForm(statistics);
    });
  }

  updateForm(statistics: IStatistics): void {
    this.editForm.patchValue({
      id: statistics.id,
      created: statistics.created ? statistics.created.format(DATE_TIME_FORMAT) : null,
      tpye: statistics.tpye,
      content: statistics.content,
      description: statistics.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const statistics = this.createFromForm();
    if (statistics.id !== undefined) {
      this.subscribeToSaveResponse(this.statisticsService.update(statistics));
    } else {
      this.subscribeToSaveResponse(this.statisticsService.create(statistics));
    }
  }

  private createFromForm(): IStatistics {
    return {
      ...new Statistics(),
      id: this.editForm.get(['id'])!.value,
      created: this.editForm.get(['created'])!.value ? moment(this.editForm.get(['created'])!.value, DATE_TIME_FORMAT) : undefined,
      tpye: this.editForm.get(['tpye'])!.value,
      content: this.editForm.get(['content'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStatistics>>): void {
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
