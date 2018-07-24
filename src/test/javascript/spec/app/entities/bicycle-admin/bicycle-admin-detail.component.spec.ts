/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Demo3TestModule } from '../../../test.module';
import { BicycleAdminDetailComponent } from 'app/entities/bicycle-admin/bicycle-admin-detail.component';
import { BicycleAdmin } from 'app/shared/model/bicycle-admin.model';

describe('Component Tests', () => {
    describe('BicycleAdmin Management Detail Component', () => {
        let comp: BicycleAdminDetailComponent;
        let fixture: ComponentFixture<BicycleAdminDetailComponent>;
        const route = ({ data: of({ bicycleAdmin: new BicycleAdmin(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Demo3TestModule],
                declarations: [BicycleAdminDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BicycleAdminDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BicycleAdminDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.bicycleAdmin).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
