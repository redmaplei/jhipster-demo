import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISteward } from 'app/shared/model/steward.model';
import { StewardService } from './steward.service';

@Component({
    selector: 'jhi-steward-delete-dialog',
    templateUrl: './steward-delete-dialog.component.html'
})
export class StewardDeleteDialogComponent {
    steward: ISteward;

    constructor(private stewardService: StewardService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.stewardService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'stewardListModification',
                content: 'Deleted an steward'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-steward-delete-popup',
    template: ''
})
export class StewardDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ steward }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(StewardDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.steward = steward;
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
