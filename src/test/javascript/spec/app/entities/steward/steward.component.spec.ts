/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Demo3TestModule } from '../../../test.module';
import { StewardComponent } from 'app/entities/steward/steward.component';
import { StewardService } from 'app/entities/steward/steward.service';
import { Steward } from 'app/shared/model/steward.model';

describe('Component Tests', () => {
    describe('Steward Management Component', () => {
        let comp: StewardComponent;
        let fixture: ComponentFixture<StewardComponent>;
        let service: StewardService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Demo3TestModule],
                declarations: [StewardComponent],
                providers: []
            })
                .overrideTemplate(StewardComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StewardComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StewardService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Steward(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.stewards[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
