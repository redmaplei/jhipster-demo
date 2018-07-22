import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Round } from 'app/shared/model/round.model';
import { RoundService } from './round.service';
import { RoundComponent } from './round.component';
import { RoundDetailComponent } from './round-detail.component';
import { RoundUpdateComponent } from './round-update.component';
import { RoundDeletePopupComponent } from './round-delete-dialog.component';
import { IRound } from 'app/shared/model/round.model';

@Injectable({ providedIn: 'root' })
export class RoundResolve implements Resolve<IRound> {
    constructor(private service: RoundService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((round: HttpResponse<Round>) => round.body));
        }
        return of(new Round());
    }
}

export const roundRoute: Routes = [
    {
        path: 'round',
        component: RoundComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.round.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'round/:id/view',
        component: RoundDetailComponent,
        resolve: {
            round: RoundResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.round.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'round/new',
        component: RoundUpdateComponent,
        resolve: {
            round: RoundResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.round.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'round/:id/edit',
        component: RoundUpdateComponent,
        resolve: {
            round: RoundResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.round.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const roundPopupRoute: Routes = [
    {
        path: 'round/:id/delete',
        component: RoundDeletePopupComponent,
        resolve: {
            round: RoundResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demo3App.round.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
