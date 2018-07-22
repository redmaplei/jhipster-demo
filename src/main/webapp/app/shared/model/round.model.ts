export interface IRound {
    id?: number;
    radius?: number;
    perimeter?: number;
    pi?: number;
}

export class Round implements IRound {
    constructor(public id?: number, public radius?: number, public perimeter?: number, public pi?: number) {}
}
