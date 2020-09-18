import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDictionaryTranslation } from 'app/shared/model/dictionary-translation.model';

@Component({
  selector: 'jhi-dictionary-translation-detail',
  templateUrl: './dictionary-translation-detail.component.html',
})
export class DictionaryTranslationDetailComponent implements OnInit {
  dictionaryTranslation: IDictionaryTranslation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dictionaryTranslation }) => (this.dictionaryTranslation = dictionaryTranslation));
  }

  previousState(): void {
    window.history.back();
  }
}
