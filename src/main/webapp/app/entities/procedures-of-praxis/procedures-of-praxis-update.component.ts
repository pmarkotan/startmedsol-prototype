import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProceduresOfPraxis, ProceduresOfPraxis } from 'app/shared/model/procedures-of-praxis.model';
import { ProceduresOfPraxisService } from './procedures-of-praxis.service';
import { ICsProcedure } from 'app/shared/model/cs-procedure.model';
import { CsProcedureService } from 'app/entities/cs-procedure/cs-procedure.service';
import { IPraxis } from 'app/shared/model/praxis.model';
import { PraxisService } from 'app/entities/praxis/praxis.service';

type SelectableEntity = ICsProcedure | IPraxis;

@Component({
  selector: 'jhi-procedures-of-praxis-update',
  templateUrl: './procedures-of-praxis-update.component.html',
})
export class ProceduresOfPraxisUpdateComponent implements OnInit {
  isSaving = false;
  csprocedures: ICsProcedure[] = [];
  praxes: IPraxis[] = [];

  editForm = this.fb.group({
    id: [],
    procedureId: [null, Validators.required],
    praxisId: [null, Validators.required],
  });

  constructor(
    protected proceduresOfPraxisService: ProceduresOfPraxisService,
    protected csProcedureService: CsProcedureService,
    protected praxisService: PraxisService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ proceduresOfPraxis }) => {
      this.updateForm(proceduresOfPraxis);

      this.csProcedureService.query().subscribe((res: HttpResponse<ICsProcedure[]>) => (this.csprocedures = res.body || []));

      this.praxisService.query().subscribe((res: HttpResponse<IPraxis[]>) => (this.praxes = res.body || []));
    });
  }

  updateForm(proceduresOfPraxis: IProceduresOfPraxis): void {
    this.editForm.patchValue({
      id: proceduresOfPraxis.id,
      procedureId: proceduresOfPraxis.procedureId,
      praxisId: proceduresOfPraxis.praxisId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const proceduresOfPraxis = this.createFromForm();
    if (proceduresOfPraxis.id !== undefined) {
      this.subscribeToSaveResponse(this.proceduresOfPraxisService.update(proceduresOfPraxis));
    } else {
      this.subscribeToSaveResponse(this.proceduresOfPraxisService.create(proceduresOfPraxis));
    }
  }

  private createFromForm(): IProceduresOfPraxis {
    return {
      ...new ProceduresOfPraxis(),
      id: this.editForm.get(['id'])!.value,
      procedureId: this.editForm.get(['procedureId'])!.value,
      praxisId: this.editForm.get(['praxisId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProceduresOfPraxis>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
