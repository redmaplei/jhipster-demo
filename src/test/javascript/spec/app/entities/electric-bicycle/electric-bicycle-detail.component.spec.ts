/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Demo3TestModule } from '../../../test.module';
import { ElectricBicycleDetailComponent } from 'app/entities/electric-bicycle/electric-bicycle-detail.component';
import { ElectricBicycle } from 'app/shared/model/electric-bicycle.model';

describe('Component Tests', () => {
    describe('ElectricBicycle Management Detail Component', () => {
        let comp: ElectricBicycleDetailComponent;
        let fixture: ComponentFixture<ElectricBicycleDetailComponent>;
        const route = ({ data: of({ electricBicycle: new ElectricBicycle(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Demo3TestModule],
                declarations: [ElectricBicycleDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ElectricBicycleDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ElectricBicycleDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.electricBicycle).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
