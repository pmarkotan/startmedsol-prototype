import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IDynamicForm } from 'app/shared/model/dynamic-form.model';

@Component({
  selector: 'jhi-dynamic-form-detail',
  templateUrl: './dynamic-form-detail.component.html',
})
export class DynamicFormDetailComponent implements OnInit {
  dynamicForm: IDynamicForm | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dynamicForm }) => (this.dynamicForm = dynamicForm));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
