import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBicycleAdmin } from 'app/shared/model/bicycle-admin.model';
import { BicycleAdminService } from './bicycle-admin.service';

@Component({
    selector: 'jhi-bicycle-admin-delete-dialog',
    templateUrl: './bicycle-admin-delete-dialog.component.html'
})
export class BicycleAdminDeleteDialogComponent {
    bicycleAdmin: IBicycleAdmin;

    constructor(
        private bicycleAdminService: BicycleAdminService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.bicycleAdminService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'bicycleAdminListModification',
                content: 'Deleted an bicycleAdmin'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-bicycle-admin-delete-popup',
    template: ''
})
export class BicycleAdminDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bicycleAdmin }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BicycleAdminDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.bicycleAdmin = bicycleAdmin;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
