/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Demo3TestModule } from '../../../test.module';
import { BicycleAdminDeleteDialogComponent } from 'app/entities/bicycle-admin/bicycle-admin-delete-dialog.component';
import { BicycleAdminService } from 'app/entities/bicycle-admin/bicycle-admin.service';

describe('Component Tests', () => {
    describe('BicycleAdmin Management Delete Component', () => {
        let comp: BicycleAdminDeleteDialogComponent;
        let fixture: ComponentFixture<BicycleAdminDeleteDialogComponent>;
        let service: BicycleAdminService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Demo3TestModule],
                declarations: [BicycleAdminDeleteDialogComponent]
            })
                .overrideTemplate(BicycleAdminDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BicycleAdminDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BicycleAdminService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
