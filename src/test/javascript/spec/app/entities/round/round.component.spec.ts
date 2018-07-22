/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Demo3TestModule } from '../../../test.module';
import { RoundComponent } from 'app/entities/round/round.component';
import { RoundService } from 'app/entities/round/round.service';
import { Round } from 'app/shared/model/round.model';

describe('Component Tests', () => {
    describe('Round Management Component', () => {
        let comp: RoundComponent;
        let fixture: ComponentFixture<RoundComponent>;
        let service: RoundService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Demo3TestModule],
                declarations: [RoundComponent],
                providers: []
            })
                .overrideTemplate(RoundComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RoundComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RoundService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Round(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.rounds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
