import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IElectricBicycle } from 'app/shared/model/electric-bicycle.model';
import { ElectricBicycleService } from './electric-bicycle.service';

@Component({
    selector: 'jhi-electric-bicycle-delete-dialog',
    templateUrl: './electric-bicycle-delete-dialog.component.html'
})
export class ElectricBicycleDeleteDialogComponent {
    electricBicycle: IElectricBicycle;

    constructor(
        private electricBicycleService: ElectricBicycleService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.electricBicycleService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'electricBicycleListModification',
                content: 'Deleted an electricBicycle'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-electric-bicycle-delete-popup',
    template: ''
})
export class ElectricBicycleDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ electricBicycle }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ElectricBicycleDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.electricBicycle = electricBicycle;
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
