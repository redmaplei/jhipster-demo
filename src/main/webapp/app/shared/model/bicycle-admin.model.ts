export interface IBicycleAdmin {
    id?: number;
    username?: string;
    password?: string;
    phonenumber?: string;
}

export class BicycleAdmin implements IBicycleAdmin {
    constructor(public id?: number, public username?: string, public password?: string, public phonenumber?: string) {}
}
