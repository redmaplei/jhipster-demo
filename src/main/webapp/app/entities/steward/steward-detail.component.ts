import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISteward } from 'app/shared/model/steward.model';

@Component({
    selector: 'jhi-steward-detail',
    templateUrl: './steward-detail.component.html'
})
export class StewardDetailComponent implements OnInit {
    steward: ISteward;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ steward }) => {
            this.steward = steward;
        });
    }

    previousState() {
        window.history.back();
    }
}
