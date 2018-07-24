/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Demo3TestModule } from '../../../test.module';
import { ElectricBicycleDeleteDialogComponent } from 'app/entities/electric-bicycle/electric-bicycle-delete-dialog.component';
import { ElectricBicycleService } from 'app/entities/electric-bicycle/electric-bicycle.service';

describe('Component Tests', () => {
    describe('ElectricBicycle Management Delete Component', () => {
        let comp: ElectricBicycleDeleteDialogComponent;
        let fixture: ComponentFixture<ElectricBicycleDeleteDialogComponent>;
        let service: ElectricBicycleService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Demo3TestModule],
                declarations: [ElectricBicycleDeleteDialogComponent]
            })
                .overrideTemplate(ElectricBicycleDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ElectricBicycleDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ElectricBicycleService);
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
