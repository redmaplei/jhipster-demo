import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISteward } from 'app/shared/model/steward.model';
import { Principal } from 'app/core';
import { StewardService } from './steward.service';

@Component({
    selector: 'jhi-steward',
    templateUrl: './steward.component.html'
})
export class StewardComponent implements OnInit, OnDestroy {
    stewards: ISteward[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private stewardService: StewardService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.stewardService.query().subscribe(
            (res: HttpResponse<ISteward[]>) => {
                this.stewards = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInStewards();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISteward) {
        return item.id;
    }

    registerChangeInStewards() {
        this.eventSubscriber = this.eventManager.subscribe('stewardListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
