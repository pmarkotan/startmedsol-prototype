import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICsCountry } from 'app/shared/model/cs-country.model';

@Component({
  selector: 'jhi-cs-country-detail',
  templateUrl: './cs-country-detail.component.html',
})
export class CsCountryDetailComponent implements OnInit {
  csCountry: ICsCountry | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ csCountry }) => (this.csCountry = csCountry));
  }

  previousState(): void {
    window.history.back();
  }
}
