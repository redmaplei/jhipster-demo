import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ISteward } from 'app/shared/model/steward.model';
import { StewardService } from './steward.service';

@Component({
    selector: 'jhi-steward-update',
    templateUrl: './steward-update.component.html'
})
export class StewardUpdateComponent implements OnInit {
    private _steward: ISteward;
    isSaving: boolean;

    constructor(private stewardService: StewardService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ steward }) => {
            this.steward = steward;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.steward.id !== undefined) {
            this.subscribeToSaveResponse(this.stewardService.update(this.steward));
        } else {
            this.subscribeToSaveResponse(this.stewardService.create(this.steward));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISteward>>) {
        result.subscribe((res: HttpResponse<ISteward>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get steward() {
        return this._steward;
    }

    set steward(steward: ISteward) {
        this._steward = steward;
    }
}
