import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Demo3SharedModule } from 'app/shared';
import {
    BicycleAdminComponent,
    BicycleAdminDetailComponent,
    BicycleAdminUpdateComponent,
    BicycleAdminDeletePopupComponent,
    BicycleAdminDeleteDialogComponent,
    bicycleAdminRoute,
    bicycleAdminPopupRoute
} from './';

const ENTITY_STATES = [...bicycleAdminRoute, ...bicycleAdminPopupRoute];

@NgModule({
    imports: [Demo3SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BicycleAdminComponent,
        BicycleAdminDetailComponent,
        BicycleAdminUpdateComponent,
        BicycleAdminDeleteDialogComponent,
        BicycleAdminDeletePopupComponent
    ],
    entryComponents: [
        BicycleAdminComponent,
        BicycleAdminUpdateComponent,
        BicycleAdminDeleteDialogComponent,
        BicycleAdminDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Demo3BicycleAdminModule {}
