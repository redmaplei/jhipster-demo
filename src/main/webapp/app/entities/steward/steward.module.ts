import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Demo3SharedModule } from 'app/shared';
import {
    StewardComponent,
    StewardDetailComponent,
    StewardUpdateComponent,
    StewardDeletePopupComponent,
    StewardDeleteDialogComponent,
    stewardRoute,
    stewardPopupRoute
} from './';

const ENTITY_STATES = [...stewardRoute, ...stewardPopupRoute];

@NgModule({
    imports: [Demo3SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StewardComponent,
        StewardDetailComponent,
        StewardUpdateComponent,
        StewardDeleteDialogComponent,
        StewardDeletePopupComponent
    ],
    entryComponents: [StewardComponent, StewardUpdateComponent, StewardDeleteDialogComponent, StewardDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Demo3StewardModule {}
