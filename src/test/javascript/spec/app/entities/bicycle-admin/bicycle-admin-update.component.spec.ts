/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Demo3TestModule } from '../../../test.module';
import { BicycleAdminUpdateComponent } from 'app/entities/bicycle-admin/bicycle-admin-update.component';
import { BicycleAdminService } from 'app/entities/bicycle-admin/bicycle-admin.service';
import { BicycleAdmin } from 'app/shared/model/bicycle-admin.model';

describe('Component Tests', () => {
    describe('BicycleAdmin Management Update Component', () => {
        let comp: BicycleAdminUpdateComponent;
        let fixture: ComponentFixture<BicycleAdminUpdateComponent>;
        let service: BicycleAdminService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Demo3TestModule],
                declarations: [BicycleAdminUpdateComponent]
            })
                .overrideTemplate(BicycleAdminUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BicycleAdminUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BicycleAdminService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BicycleAdmin(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.bicycleAdmin = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BicycleAdmin();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.bicycleAdmin = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
