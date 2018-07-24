import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IElectricBicycle } from 'app/shared/model/electric-bicycle.model';

@Component({
    selector: 'jhi-electric-bicycle-detail',
    templateUrl: './electric-bicycle-detail.component.html'
})
export class ElectricBicycleDetailComponent implements OnInit {
    electricBicycle: IElectricBicycle;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ electricBicycle }) => {
            this.electricBicycle = electricBicycle;
        });
    }

    previousState() {
        window.history.back();
    }
}
