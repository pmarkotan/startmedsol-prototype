import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDictionaryItem } from 'app/shared/model/dictionary-item.model';

@Component({
  selector: 'jhi-dictionary-item-detail',
  templateUrl: './dictionary-item-detail.component.html',
})
export class DictionaryItemDetailComponent implements OnInit {
  dictionaryItem: IDictionaryItem | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dictionaryItem }) => (this.dictionaryItem = dictionaryItem));
  }

  previousState(): void {
    window.history.back();
  }
}
