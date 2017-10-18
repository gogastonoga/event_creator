export class Event {
    budget: number;
    type: boolean;
    details: string;
    eventDate: Date;
    size: string;
    nights: number;
    additionalRequirements: string;
    maxCost: number;
    organizer: Organizer;
}

  export class Organizer {
    name: string;
    surname: string;
    phoneNumber: string;
    email: String;
  }
