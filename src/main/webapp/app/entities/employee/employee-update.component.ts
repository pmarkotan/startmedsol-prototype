import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEmployee, Employee } from 'app/shared/model/employee.model';
import { EmployeeService } from './employee.service';
import { IPersonalData } from 'app/shared/model/personal-data.model';
import { PersonalDataService } from 'app/entities/personal-data/personal-data.service';

@Component({
  selector: 'jhi-employee-update',
  templateUrl: './employee-update.component.html',
})
export class EmployeeUpdateComponent implements OnInit {
  isSaving = false;
  personaldata: IPersonalData[] = [];

  editForm = this.fb.group({
    id: [],
    emlpoyeeType: [null, [Validators.required]],
    identifier: [null, [Validators.required, Validators.maxLength(10)]],
    eesztIdentifier: [null, [Validators.maxLength(10)]],
    eesztLoginType: [null, [Validators.required]],
    personalData: [null, Validators.required],
  });

  constructor(
    protected employeeService: EmployeeService,
    protected personalDataService: PersonalDataService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ employee }) => {
      this.updateForm(employee);

      this.personalDataService
        .query({ filter: 'employee-is-null' })
        .pipe(
          map((res: HttpResponse<IPersonalData[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPersonalData[]) => {
          if (!employee.personalData || !employee.personalData.id) {
            this.personaldata = resBody;
          } else {
            this.personalDataService
              .find(employee.personalData.id)
              .pipe(
                map((subRes: HttpResponse<IPersonalData>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPersonalData[]) => (this.personaldata = concatRes));
          }
        });
    });
  }

  updateForm(employee: IEmployee): void {
    this.editForm.patchValue({
      id: employee.id,
      emlpoyeeType: employee.emlpoyeeType,
      identifier: employee.identifier,
      eesztIdentifier: employee.eesztIdentifier,
      eesztLoginType: employee.eesztLoginType,
      personalData: employee.personalData,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const employee = this.createFromForm();
    if (employee.id !== undefined) {
      this.subscribeToSaveResponse(this.employeeService.update(employee));
    } else {
      this.subscribeToSaveResponse(this.employeeService.create(employee));
    }
  }

  private createFromForm(): IEmployee {
    return {
      ...new Employee(),
      id: this.editForm.get(['id'])!.value,
      emlpoyeeType: this.editForm.get(['emlpoyeeType'])!.value,
      identifier: this.editForm.get(['identifier'])!.value,
      eesztIdentifier: this.editForm.get(['eesztIdentifier'])!.value,
      eesztLoginType: this.editForm.get(['eesztLoginType'])!.value,
      personalData: this.editForm.get(['personalData'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmployee>>): void {
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

  trackById(index: number, item: IPersonalData): any {
    return item.id;
  }
}
