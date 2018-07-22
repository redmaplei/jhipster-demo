import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Demo3SharedModule } from 'app/shared';
import {
    RoundComponent,
    RoundDetailComponent,
    RoundUpdateComponent,
    RoundDeletePopupComponent,
    RoundDeleteDialogComponent,
    roundRoute,
    roundPopupRoute
} from './';

const ENTITY_STATES = [...roundRoute, ...roundPopupRoute];

@NgModule({
    imports: [Demo3SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [RoundComponent, RoundDetailComponent, RoundUpdateComponent, RoundDeleteDialogComponent, RoundDeletePopupComponent],
    entryComponents: [RoundComponent, RoundUpdateComponent, RoundDeleteDialogComponent, RoundDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Demo3RoundModule {}
