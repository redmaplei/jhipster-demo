import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBicycleAdmin } from 'app/shared/model/bicycle-admin.model';

type EntityResponseType = HttpResponse<IBicycleAdmin>;
type EntityArrayResponseType = HttpResponse<IBicycleAdmin[]>;

@Injectable({ providedIn: 'root' })
export class BicycleAdminService {
    private resourceUrl = SERVER_API_URL + 'api/bicycle-admins';

    constructor(private http: HttpClient) {}

    create(bicycleAdmin: IBicycleAdmin): Observable<EntityResponseType> {
        return this.http.post<IBicycleAdmin>(this.resourceUrl, bicycleAdmin, { observe: 'response' });
    }

    update(bicycleAdmin: IBicycleAdmin): Observable<EntityResponseType> {
        return this.http.put<IBicycleAdmin>(this.resourceUrl, bicycleAdmin, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBicycleAdmin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBicycleAdmin[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
