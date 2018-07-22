import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRound } from 'app/shared/model/round.model';
import { Principal } from 'app/core';
import { RoundService } from './round.service';

@Component({
    selector: 'jhi-round',
    templateUrl: './round.component.html'
})
export class RoundComponent implements OnInit, OnDestroy {
    rounds: IRound[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private roundService: RoundService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.roundService.query().subscribe(
            (res: HttpResponse<IRound[]>) => {
                this.rounds = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRounds();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRound) {
        return item.id;
    }

    registerChangeInRounds() {
        this.eventSubscriber = this.eventManager.subscribe('roundListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
