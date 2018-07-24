/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Demo3TestModule } from '../../../test.module';
import { BicycleAdminComponent } from 'app/entities/bicycle-admin/bicycle-admin.component';
import { BicycleAdminService } from 'app/entities/bicycle-admin/bicycle-admin.service';
import { BicycleAdmin } from 'app/shared/model/bicycle-admin.model';

describe('Component Tests', () => {
    describe('BicycleAdmin Management Component', () => {
        let comp: BicycleAdminComponent;
        let fixture: ComponentFixture<BicycleAdminComponent>;
        let service: BicycleAdminService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Demo3TestModule],
                declarations: [BicycleAdminComponent],
                providers: []
            })
                .overrideTemplate(BicycleAdminComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BicycleAdminComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BicycleAdminService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new BicycleAdmin(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.bicycleAdmins[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
