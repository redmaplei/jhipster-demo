/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Demo3TestModule } from '../../../test.module';
import { ElectricBicycleComponent } from 'app/entities/electric-bicycle/electric-bicycle.component';
import { ElectricBicycleService } from 'app/entities/electric-bicycle/electric-bicycle.service';
import { ElectricBicycle } from 'app/shared/model/electric-bicycle.model';

describe('Component Tests', () => {
    describe('ElectricBicycle Management Component', () => {
        let comp: ElectricBicycleComponent;
        let fixture: ComponentFixture<ElectricBicycleComponent>;
        let service: ElectricBicycleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Demo3TestModule],
                declarations: [ElectricBicycleComponent],
                providers: []
            })
                .overrideTemplate(ElectricBicycleComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ElectricBicycleComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ElectricBicycleService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ElectricBicycle(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.electricBicycles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
