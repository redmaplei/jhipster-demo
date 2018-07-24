export interface ISteward {
    id?: number;
    stewardID?: string;
    stewardName?: string;
    stewardInfo?: string;
}

export class Steward implements ISteward {
    constructor(public id?: number, public stewardID?: string, public stewardName?: string, public stewardInfo?: string) {}
}
