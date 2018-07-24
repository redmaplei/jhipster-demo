import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IElectricBicycle } from 'app/shared/model/electric-bicycle.model';
import { Principal } from 'app/core';
import { ElectricBicycleService } from './electric-bicycle.service';

@Component({
    selector: 'jhi-electric-bicycle',
    templateUrl: './electric-bicycle.component.html'
})
export class ElectricBicycleComponent implements OnInit, OnDestroy {
    electricBicycles: IElectricBicycle[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private electricBicycleService: ElectricBicycleService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.electricBicycleService.query().subscribe(
            (res: HttpResponse<IElectricBicycle[]>) => {
                this.electricBicycles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInElectricBicycles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IElectricBicycle) {
        return item.id;
    }

    registerChangeInElectricBicycles() {
        this.eventSubscriber = this.eventManager.subscribe('electricBicycleListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
