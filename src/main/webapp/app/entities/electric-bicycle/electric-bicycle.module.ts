import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Demo3SharedModule } from 'app/shared';
import {
    ElectricBicycleComponent,
    ElectricBicycleDetailComponent,
    ElectricBicycleUpdateComponent,
    ElectricBicycleDeletePopupComponent,
    ElectricBicycleDeleteDialogComponent,
    electricBicycleRoute,
    electricBicyclePopupRoute
} from './';

const ENTITY_STATES = [...electricBicycleRoute, ...electricBicyclePopupRoute];

@NgModule({
    imports: [Demo3SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ElectricBicycleComponent,
        ElectricBicycleDetailComponent,
        ElectricBicycleUpdateComponent,
        ElectricBicycleDeleteDialogComponent,
        ElectricBicycleDeletePopupComponent
    ],
    entryComponents: [
        ElectricBicycleComponent,
        ElectricBicycleUpdateComponent,
        ElectricBicycleDeleteDialogComponent,
        ElectricBicycleDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Demo3ElectricBicycleModule {}
