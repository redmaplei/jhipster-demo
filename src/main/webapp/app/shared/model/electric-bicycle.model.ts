export interface IElectricBicycle {
    id?: number;
    bicycleID?: string;
    bicycleModel?: string;
    bicycleInfo?: string;
}

export class ElectricBicycle implements IElectricBicycle {
    constructor(public id?: number, public bicycleID?: string, public bicycleModel?: string, public bicycleInfo?: string) {}
}
