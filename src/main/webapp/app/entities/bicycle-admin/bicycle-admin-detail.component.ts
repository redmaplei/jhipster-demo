import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBicycleAdmin } from 'app/shared/model/bicycle-admin.model';

@Component({
    selector: 'jhi-bicycle-admin-detail',
    templateUrl: './bicycle-admin-detail.component.html'
})
export class BicycleAdminDetailComponent implements OnInit {
    bicycleAdmin: IBicycleAdmin;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bicycleAdmin }) => {
            this.bicycleAdmin = bicycleAdmin;
        });
    }

    previousState() {
        window.history.back();
    }
}
