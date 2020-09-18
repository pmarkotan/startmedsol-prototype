import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INavSettings } from 'app/shared/model/nav-settings.model';
import { NavSettingsService } from './nav-settings.service';

@Component({
  templateUrl: './nav-settings-delete-dialog.component.html',
})
export class NavSettingsDeleteDialogComponent {
  navSettings?: INavSettings;

  constructor(
    protected navSettingsService: NavSettingsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.navSettingsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('navSettingsListModification');
      this.activeModal.close();
    });
  }
}
