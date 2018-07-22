import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRound } from 'app/shared/model/round.model';
import { RoundService } from './round.service';

@Component({
    selector: 'jhi-round-delete-dialog',
    templateUrl: './round-delete-dialog.component.html'
})
export class RoundDeleteDialogComponent {
    round: IRound;

    constructor(private roundService: RoundService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.roundService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'roundListModification',
                content: 'Deleted an round'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-round-delete-popup',
    template: ''
})
export class RoundDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ round }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RoundDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.round = round;
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
