import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFeedBackMessage, FeedBackMessage } from 'app/shared/model/feed-back-message.model';
import { FeedBackMessageService } from './feed-back-message.service';

@Component({
  selector: 'jhi-feed-back-message-update',
  templateUrl: './feed-back-message-update.component.html',
})
export class FeedBackMessageUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    createDate: [null, [Validators.required]],
    author: [],
    type: [null, [Validators.required]],
    content: [null, [Validators.required, Validators.maxLength(1024)]],
  });

  constructor(
    protected feedBackMessageService: FeedBackMessageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ feedBackMessage }) => {
      if (!feedBackMessage.id) {
        const today = moment().startOf('day');
        feedBackMessage.createDate = today;
      }

      this.updateForm(feedBackMessage);
    });
  }

  updateForm(feedBackMessage: IFeedBackMessage): void {
    this.editForm.patchValue({
      id: feedBackMessage.id,
      createDate: feedBackMessage.createDate ? feedBackMessage.createDate.format(DATE_TIME_FORMAT) : null,
      author: feedBackMessage.author,
      type: feedBackMessage.type,
      content: feedBackMessage.content,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const feedBackMessage = this.createFromForm();
    if (feedBackMessage.id !== undefined) {
      this.subscribeToSaveResponse(this.feedBackMessageService.update(feedBackMessage));
    } else {
      this.subscribeToSaveResponse(this.feedBackMessageService.create(feedBackMessage));
    }
  }

  private createFromForm(): IFeedBackMessage {
    return {
      ...new FeedBackMessage(),
      id: this.editForm.get(['id'])!.value,
      createDate: this.editForm.get(['createDate'])!.value ? moment(this.editForm.get(['createDate'])!.value, DATE_TIME_FORMAT) : undefined,
      author: this.editForm.get(['author'])!.value,
      type: this.editForm.get(['type'])!.value,
      content: this.editForm.get(['content'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFeedBackMessage>>): void {
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
