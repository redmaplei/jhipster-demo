import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IBicycleAdmin } from 'app/shared/model/bicycle-admin.model';
import { Principal } from 'app/core';
import { BicycleAdminService } from './bicycle-admin.service';

@Component({
    selector: 'jhi-bicycle-admin',
    templateUrl: './bicycle-admin.component.html'
})
export class BicycleAdminComponent implements OnInit, OnDestroy {
    bicycleAdmins: IBicycleAdmin[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private bicycleAdminService: BicycleAdminService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.bicycleAdminService.query().subscribe(
            (res: HttpResponse<IBicycleAdmin[]>) => {
                this.bicycleAdmins = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInBicycleAdmins();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBicycleAdmin) {
        return item.id;
    }

    registerChangeInBicycleAdmins() {
        this.eventSubscriber = this.eventManager.subscribe('bicycleAdminListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
