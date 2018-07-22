import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Demo3RoundModule } from './round/round.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        Demo3RoundModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Demo3EntityModule {}
