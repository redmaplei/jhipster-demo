import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Steward } from 'app/shared/model/steward.model';
import { StewardService } from './steward.service';
import { StewardComponent } from './steward.component';
import { StewardDetailComponent } from './steward-detail.component';
import { StewardUpdateComponent } from './steward-update.component';
import { StewardDeletePopupComponent } from './steward-delete-dialog.component';
import { ISteward } from 'app/shared/model/steward.model';

@Injectable({ providedIn: 'root' })
export class StewardResolve implements Resolve<ISteward> {
    constructor(private service: StewardService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((steward: HttpResponse<Steward>) => steward.body));
        }
        return of(new Steward());
    }
}

export const stewardRoute: Routes = [
    {
        path: 'steward',
        component: StewardComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.steward.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'steward/:id/view',
        component: StewardDetailComponent,
        resolve: {
            steward: StewardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.steward.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'steward/new',
        component: StewardUpdateComponent,
        resolve: {
            steward: StewardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.steward.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'steward/:id/edit',
        component: StewardUpdateComponent,
        resolve: {
            steward: StewardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.steward.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const stewardPopupRoute: Routes = [
    {
        path: 'steward/:id/delete',
        component: StewardDeletePopupComponent,
        resolve: {
            steward: StewardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.steward.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
