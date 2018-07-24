/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Demo3TestModule } from '../../../test.module';
import { StewardUpdateComponent } from 'app/entities/steward/steward-update.component';
import { StewardService } from 'app/entities/steward/steward.service';
import { Steward } from 'app/shared/model/steward.model';

describe('Component Tests', () => {
    describe('Steward Management Update Component', () => {
        let comp: StewardUpdateComponent;
        let fixture: ComponentFixture<StewardUpdateComponent>;
        let service: StewardService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Demo3TestModule],
                declarations: [StewardUpdateComponent]
            })
                .overrideTemplate(StewardUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StewardUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StewardService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Steward(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.steward = entity;
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
                    const entity = new Steward();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.steward = entity;
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
