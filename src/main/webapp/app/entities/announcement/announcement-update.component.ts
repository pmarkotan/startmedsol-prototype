import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IAnnouncement, Announcement } from 'app/shared/model/announcement.model';
import { AnnouncementService } from './announcement.service';

@Component({
  selector: 'jhi-announcement-update',
  templateUrl: './announcement-update.component.html',
})
export class AnnouncementUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    publishingDate: [null, [Validators.required]],
    expireDate: [null, [Validators.required]],
    location: [null, [Validators.required]],
    type: [null, [Validators.required]],
    content: [null, [Validators.required, Validators.maxLength(1024)]],
  });

  constructor(protected announcementService: AnnouncementService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ announcement }) => {
      if (!announcement.id) {
        const today = moment().startOf('day');
        announcement.publishingDate = today;
        announcement.expireDate = today;
      }

      this.updateForm(announcement);
    });
  }

  updateForm(announcement: IAnnouncement): void {
    this.editForm.patchValue({
      id: announcement.id,
      publishingDate: announcement.publishingDate ? announcement.publishingDate.format(DATE_TIME_FORMAT) : null,
      expireDate: announcement.expireDate ? announcement.expireDate.format(DATE_TIME_FORMAT) : null,
      location: announcement.location,
      type: announcement.type,
      content: announcement.content,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const announcement = this.createFromForm();
    if (announcement.id !== undefined) {
      this.subscribeToSaveResponse(this.announcementService.update(announcement));
    } else {
      this.subscribeToSaveResponse(this.announcementService.create(announcement));
    }
  }

  private createFromForm(): IAnnouncement {
    return {
      ...new Announcement(),
      id: this.editForm.get(['id'])!.value,
      publishingDate: this.editForm.get(['publishingDate'])!.value
        ? moment(this.editForm.get(['publishingDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      expireDate: this.editForm.get(['expireDate'])!.value ? moment(this.editForm.get(['expireDate'])!.value, DATE_TIME_FORMAT) : undefined,
      location: this.editForm.get(['location'])!.value,
      type: this.editForm.get(['type'])!.value,
      content: this.editForm.get(['content'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnnouncement>>): void {
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
