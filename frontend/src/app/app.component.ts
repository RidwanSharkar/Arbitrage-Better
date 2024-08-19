// frontend/src/app/app.component.ts
import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule  } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { CommonModule } from '@angular/common';


export interface Fight {
    eventTitle: string;
    fighterOne: string;
    fighterTwo: string;
    odds: {
        [fighter: string]: {
            [bookmaker: string]: string;
        };
    };
    arbitragePossible?: boolean;
    arbitrageDetails?: {
        bookmaker1: string;
        bookmaker2: string;
        profit: number;
    };
}

export interface EventGroup {
  eventTitle: string;
  fights: Fight[];
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true,
  imports: [CommonModule, HttpClientModule]
})

export class AppComponent implements OnInit {
  title = 'Arbitrager';
  eventGroups: EventGroup[] = [];
  fights: Fight[] = [];
  bookmakers: string[] = ['DraftKings', 'BetMGM', 'Caesars', 'BetRivers', 'FanDuel', 'BetWay'];
  private apiUrl = 'http://localhost:8080/scrape-fights';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getFights().subscribe({
        next: (data) => {
            this.calculateArbitrageOpportunities(data);
            this.groupFightsByEvent(data);
        },
        error: (error) => console.error('Error fetching fights:', error)
    });
  }

  getFights(): Observable<Fight[]> {
    return this.http.get<Fight[]>(this.apiUrl).pipe(
      catchError(error => {
        throw 'Error in getting fights data: ' + error;
      })
    );
  }

  getOdds(fight: Fight, fighter: string, bookmaker: string): string {
    return fight.odds[fighter]?.[bookmaker] || '-';
  }

  groupFightsByEvent(fights: Fight[]): void {
    const grouped = fights.reduce((acc, fight) => {
        const group = acc.find(g => g.eventTitle === fight.eventTitle);
        if (group) {
            group.fights.push(fight);
        } else {
            acc.push({ eventTitle: fight.eventTitle, fights: [fight] });
        }
        return acc;
    }, [] as EventGroup[]);
    this.eventGroups = grouped;
  }

  /*===========================================================================*/


  calculateArbitrageOpportunities(fights: Fight[]): void {
    for (let fight of fights) {
      fight.arbitragePossible = false;
      for (const bookmaker1 of this.bookmakers) {
        for (const bookmaker2 of this.bookmakers) {
          if (bookmaker1 !== bookmaker2) {
            const odds1 = parseFloat(fight.odds[fight.fighterOne][bookmaker1]);
            const odds2 = parseFloat(fight.odds[fight.fighterTwo][bookmaker2]);

            if (!isNaN(odds1) && !isNaN(odds2)) {
              const result = this.calculateArbitrage(odds1, odds2);

              if (result.isProfitable) {
                fight.arbitragePossible = true;
                fight.arbitrageDetails = {
                  bookmaker1,
                  bookmaker2,
                  profit: result.profit
                };
                break;
              }
            }
          }
        }
        if (fight.arbitragePossible) break;
      }
    }
  }

  calculateArbitrage(odds1: number, odds2: number): { isProfitable: boolean, profit: number } {
    const decimal1 = this.americanToDecimal(odds1);
    const decimal2 = this.americanToDecimal(odds2);
    
    const impliedProbability = (1 / decimal1) + (1 / decimal2);
    
    if (impliedProbability < 1) {
      const profit = (1 / impliedProbability) - 1;
      return { isProfitable: true, profit: profit * 100 };
    }
    
    return { isProfitable: false, profit: 0 };
  }

  private americanToDecimal(americanOdds: number): number {
    if (americanOdds > 0) {
      return (americanOdds / 100) + 1;
    } else {
      return (100 / Math.abs(americanOdds)) + 1;
    }
  }

  isArbitrageAvailable(): boolean {
    return this.eventGroups.some(group => group.fights.some(fight => fight.arbitragePossible));
  }

}
