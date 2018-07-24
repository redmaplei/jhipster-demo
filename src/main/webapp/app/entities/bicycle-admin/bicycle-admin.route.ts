import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BicycleAdmin } from 'app/shared/model/bicycle-admin.model';
import { BicycleAdminService } from './bicycle-admin.service';
import { BicycleAdminComponent } from './bicycle-admin.component';
import { BicycleAdminDetailComponent } from './bicycle-admin-detail.component';
import { BicycleAdminUpdateComponent } from './bicycle-admin-update.component';
import { BicycleAdminDeletePopupComponent } from './bicycle-admin-delete-dialog.component';
import { IBicycleAdmin } from 'app/shared/model/bicycle-admin.model';

@Injectable({ providedIn: 'root' })
export class BicycleAdminResolve implements Resolve<IBicycleAdmin> {
    constructor(private service: BicycleAdminService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((bicycleAdmin: HttpResponse<BicycleAdmin>) => bicycleAdmin.body));
        }
        return of(new BicycleAdmin());
    }
}

export const bicycleAdminRoute: Routes = [
    {
        path: 'bicycle-admin',
        component: BicycleAdminComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.bicycleAdmin.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bicycle-admin/:id/view',
        component: BicycleAdminDetailComponent,
        resolve: {
            bicycleAdmin: BicycleAdminResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.bicycleAdmin.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bicycle-admin/new',
        component: BicycleAdminUpdateComponent,
        resolve: {
            bicycleAdmin: BicycleAdminResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.bicycleAdmin.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bicycle-admin/:id/edit',
        component: BicycleAdminUpdateComponent,
        resolve: {
            bicycleAdmin: BicycleAdminResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.bicycleAdmin.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bicycleAdminPopupRoute: Routes = [
    {
        path: 'bicycle-admin/:id/delete',
        component: BicycleAdminDeletePopupComponent,
        resolve: {
            bicycleAdmin: BicycleAdminResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.bicycleAdmin.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
