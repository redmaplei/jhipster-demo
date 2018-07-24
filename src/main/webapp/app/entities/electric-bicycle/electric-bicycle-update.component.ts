import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IElectricBicycle } from 'app/shared/model/electric-bicycle.model';
import { ElectricBicycleService } from './electric-bicycle.service';

@Component({
    selector: 'jhi-electric-bicycle-update',
    templateUrl: './electric-bicycle-update.component.html'
})
export class ElectricBicycleUpdateComponent implements OnInit {
    private _electricBicycle: IElectricBicycle;
    isSaving: boolean;

    constructor(private electricBicycleService: ElectricBicycleService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ electricBicycle }) => {
            this.electricBicycle = electricBicycle;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.electricBicycle.id !== undefined) {
            this.subscribeToSaveResponse(this.electricBicycleService.update(this.electricBicycle));
        } else {
            this.subscribeToSaveResponse(this.electricBicycleService.create(this.electricBicycle));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IElectricBicycle>>) {
        result.subscribe((res: HttpResponse<IElectricBicycle>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get electricBicycle() {
        return this._electricBicycle;
    }

    set electricBicycle(electricBicycle: IElectricBicycle) {
        this._electricBicycle = electricBicycle;
    }
}
