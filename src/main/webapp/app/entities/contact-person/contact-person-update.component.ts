import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IContactPerson, ContactPerson } from 'app/shared/model/contact-person.model';
import { ContactPersonService } from './contact-person.service';

@Component({
  selector: 'jhi-contact-person-update',
  templateUrl: './contact-person-update.component.html',
})
export class ContactPersonUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(50)]],
    phone: [null, [Validators.required, Validators.maxLength(50)]],
    email: [null, [Validators.required, Validators.maxLength(100)]],
  });

  constructor(protected contactPersonService: ContactPersonService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contactPerson }) => {
      this.updateForm(contactPerson);
    });
  }

  updateForm(contactPerson: IContactPerson): void {
    this.editForm.patchValue({
      id: contactPerson.id,
      name: contactPerson.name,
      phone: contactPerson.phone,
      email: contactPerson.email,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const contactPerson = this.createFromForm();
    if (contactPerson.id !== undefined) {
      this.subscribeToSaveResponse(this.contactPersonService.update(contactPerson));
    } else {
      this.subscribeToSaveResponse(this.contactPersonService.create(contactPerson));
    }
  }

  private createFromForm(): IContactPerson {
    return {
      ...new ContactPerson(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      email: this.editForm.get(['email'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContactPerson>>): void {
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
