/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Demo3TestModule } from '../../../test.module';
import { StewardDeleteDialogComponent } from 'app/entities/steward/steward-delete-dialog.component';
import { StewardService } from 'app/entities/steward/steward.service';

describe('Component Tests', () => {
    describe('Steward Management Delete Component', () => {
        let comp: StewardDeleteDialogComponent;
        let fixture: ComponentFixture<StewardDeleteDialogComponent>;
        let service: StewardService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Demo3TestModule],
                declarations: [StewardDeleteDialogComponent]
            })
                .overrideTemplate(StewardDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StewardDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StewardService);
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
