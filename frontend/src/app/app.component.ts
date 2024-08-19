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
    return fight.odds[fighter]?.[bookmaker] || 'N/A';
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
}