import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IElectricBicycle } from 'app/shared/model/electric-bicycle.model';

type EntityResponseType = HttpResponse<IElectricBicycle>;
type EntityArrayResponseType = HttpResponse<IElectricBicycle[]>;

@Injectable({ providedIn: 'root' })
export class ElectricBicycleService {
    private resourceUrl = SERVER_API_URL + 'api/electric-bicycles';

    constructor(private http: HttpClient) {}

    create(electricBicycle: IElectricBicycle): Observable<EntityResponseType> {
        return this.http.post<IElectricBicycle>(this.resourceUrl, electricBicycle, { observe: 'response' });
    }

    update(electricBicycle: IElectricBicycle): Observable<EntityResponseType> {
        return this.http.put<IElectricBicycle>(this.resourceUrl, electricBicycle, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IElectricBicycle>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IElectricBicycle[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
