import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IBicycleAdmin } from 'app/shared/model/bicycle-admin.model';
import { BicycleAdminService } from './bicycle-admin.service';

@Component({
    selector: 'jhi-bicycle-admin-update',
    templateUrl: './bicycle-admin-update.component.html'
})
export class BicycleAdminUpdateComponent implements OnInit {
    private _bicycleAdmin: IBicycleAdmin;
    isSaving: boolean;

    constructor(private bicycleAdminService: BicycleAdminService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ bicycleAdmin }) => {
            this.bicycleAdmin = bicycleAdmin;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.bicycleAdmin.id !== undefined) {
            this.subscribeToSaveResponse(this.bicycleAdminService.update(this.bicycleAdmin));
        } else {
            this.subscribeToSaveResponse(this.bicycleAdminService.create(this.bicycleAdmin));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBicycleAdmin>>) {
        result.subscribe((res: HttpResponse<IBicycleAdmin>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get bicycleAdmin() {
        return this._bicycleAdmin;
    }

    set bicycleAdmin(bicycleAdmin: IBicycleAdmin) {
        this._bicycleAdmin = bicycleAdmin;
    }
}
