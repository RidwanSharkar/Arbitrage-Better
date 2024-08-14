// src/app/models/fight.model.ts

export interface Fight {
    eventTitle: string;
    fighterOne: string;
    fighterTwo: string;
    odds: {
      [fighter: string]: {
        [bookmaker: string]: string;
      };
    };
  }