/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Demo3TestModule } from '../../../test.module';
import { ElectricBicycleUpdateComponent } from 'app/entities/electric-bicycle/electric-bicycle-update.component';
import { ElectricBicycleService } from 'app/entities/electric-bicycle/electric-bicycle.service';
import { ElectricBicycle } from 'app/shared/model/electric-bicycle.model';

describe('Component Tests', () => {
    describe('ElectricBicycle Management Update Component', () => {
        let comp: ElectricBicycleUpdateComponent;
        let fixture: ComponentFixture<ElectricBicycleUpdateComponent>;
        let service: ElectricBicycleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Demo3TestModule],
                declarations: [ElectricBicycleUpdateComponent]
            })
                .overrideTemplate(ElectricBicycleUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ElectricBicycleUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ElectricBicycleService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ElectricBicycle(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.electricBicycle = entity;
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
                    const entity = new ElectricBicycle();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.electricBicycle = entity;
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
