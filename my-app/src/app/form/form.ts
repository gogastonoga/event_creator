export class Form {
    budgetDescription: string;
    additionalDescription: string;
    participantsDescription: string;
    accommodationDescription: string;
    dateFormDescription: string;
    summaryDescription: string;
}

export class Summing {
    season: string;
    bounds: string;
    days: string;
    date: string;
}

export class editSeasons {
    globalId: string;
    name: string;
    from: string;
    to: string;
}

export class editEventTypes {
    globalId: string;
    description: string;
}

export class Description {
    value: string;
}

export class Dupa {
    eventTypes: editEventTypes[];
}

export class DupaSeasons {
    seasons: editSeasons[];
}