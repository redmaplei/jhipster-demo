/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Demo3TestModule } from '../../../test.module';
import { StewardDetailComponent } from 'app/entities/steward/steward-detail.component';
import { Steward } from 'app/shared/model/steward.model';

describe('Component Tests', () => {
    describe('Steward Management Detail Component', () => {
        let comp: StewardDetailComponent;
        let fixture: ComponentFixture<StewardDetailComponent>;
        const route = ({ data: of({ steward: new Steward(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Demo3TestModule],
                declarations: [StewardDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StewardDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StewardDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.steward).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
