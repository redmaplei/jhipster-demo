import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ElectricBicycle } from 'app/shared/model/electric-bicycle.model';
import { ElectricBicycleService } from './electric-bicycle.service';
import { ElectricBicycleComponent } from './electric-bicycle.component';
import { ElectricBicycleDetailComponent } from './electric-bicycle-detail.component';
import { ElectricBicycleUpdateComponent } from './electric-bicycle-update.component';
import { ElectricBicycleDeletePopupComponent } from './electric-bicycle-delete-dialog.component';
import { IElectricBicycle } from 'app/shared/model/electric-bicycle.model';

@Injectable({ providedIn: 'root' })
export class ElectricBicycleResolve implements Resolve<IElectricBicycle> {
    constructor(private service: ElectricBicycleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((electricBicycle: HttpResponse<ElectricBicycle>) => electricBicycle.body));
        }
        return of(new ElectricBicycle());
    }
}

export const electricBicycleRoute: Routes = [
    {
        path: 'electric-bicycle',
        component: ElectricBicycleComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.electricBicycle.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'electric-bicycle/:id/view',
        component: ElectricBicycleDetailComponent,
        resolve: {
            electricBicycle: ElectricBicycleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.electricBicycle.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'electric-bicycle/new',
        component: ElectricBicycleUpdateComponent,
        resolve: {
            electricBicycle: ElectricBicycleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.electricBicycle.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'electric-bicycle/:id/edit',
        component: ElectricBicycleUpdateComponent,
        resolve: {
            electricBicycle: ElectricBicycleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.electricBicycle.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const electricBicyclePopupRoute: Routes = [
    {
        path: 'electric-bicycle/:id/delete',
        component: ElectricBicycleDeletePopupComponent,
        resolve: {
            electricBicycle: ElectricBicycleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.electricBicycle.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
