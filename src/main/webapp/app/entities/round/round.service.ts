import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRound } from 'app/shared/model/round.model';

type EntityResponseType = HttpResponse<IRound>;
type EntityArrayResponseType = HttpResponse<IRound[]>;

@Injectable({ providedIn: 'root' })
export class RoundService {
    private resourceUrl = SERVER_API_URL + 'api/rounds';

    constructor(private http: HttpClient) {}

    create(round: IRound): Observable<EntityResponseType> {
        return this.http.post<IRound>(this.resourceUrl, round, { observe: 'response' });
    }

    update(round: IRound): Observable<EntityResponseType> {
        return this.http.put<IRound>(this.resourceUrl, round, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRound>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRound[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
