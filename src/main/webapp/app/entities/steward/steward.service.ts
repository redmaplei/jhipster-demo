import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISteward } from 'app/shared/model/steward.model';

type EntityResponseType = HttpResponse<ISteward>;
type EntityArrayResponseType = HttpResponse<ISteward[]>;

@Injectable({ providedIn: 'root' })
export class StewardService {
    private resourceUrl = SERVER_API_URL + 'api/stewards';

    constructor(private http: HttpClient) {}

    create(steward: ISteward): Observable<EntityResponseType> {
        return this.http.post<ISteward>(this.resourceUrl, steward, { observe: 'response' });
    }

    update(steward: ISteward): Observable<EntityResponseType> {
        return this.http.put<ISteward>(this.resourceUrl, steward, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISteward>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISteward[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
