import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INavSettings } from 'app/shared/model/nav-settings.model';

@Component({
  selector: 'jhi-nav-settings-detail',
  templateUrl: './nav-settings-detail.component.html',
})
export class NavSettingsDetailComponent implements OnInit {
  navSettings: INavSettings | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ navSettings }) => (this.navSettings = navSettings));
  }

  previousState(): void {
    window.history.back();
  }
}
