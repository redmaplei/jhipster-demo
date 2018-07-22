import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IRound } from 'app/shared/model/round.model';
import { RoundService } from './round.service';

@Component({
    selector: 'jhi-round-update',
    templateUrl: './round-update.component.html'
})
export class RoundUpdateComponent implements OnInit {
    private _round: IRound;
    isSaving: boolean;

    constructor(private roundService: RoundService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ round }) => {
            this.round = round;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.round.id !== undefined) {
            this.subscribeToSaveResponse(this.roundService.update(this.round));
        } else {
            this.subscribeToSaveResponse(this.roundService.create(this.round));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRound>>) {
        result.subscribe((res: HttpResponse<IRound>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get round() {
        return this._round;
    }

    set round(round: IRound) {
        this._round = round;
    }
}
