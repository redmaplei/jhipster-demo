import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Demo3RoundModule } from './round/round.module';
import { Demo3BicycleAdminModule } from './bicycle-admin/bicycle-admin.module';
import { Demo3ElectricBicycleModule } from './electric-bicycle/electric-bicycle.module';
import { Demo3StewardModule } from './steward/steward.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        Demo3RoundModule,
        Demo3BicycleAdminModule,
        Demo3ElectricBicycleModule,
        Demo3StewardModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Demo3EntityModule {}
