// src/app/fights/fight.components.ts

import { Component, OnInit } from '@angular/core';
import { FightService } from './fight.service';
import { Fight } from './fight.model';

@Component({
  selector: 'app-fights',
  templateUrl: './fights.component.html',
  styleUrls: ['./fights.component.css']
})

export class FightsComponent implements OnInit {
  fights: Fight[] = [];
  bookmakers: string[] = ['DraftKings', 'BetMGM', 'Caesars', 'BetRivers', 'FanDuel', 'BetWay'];

  constructor(private fightService: FightService) {}

  ngOnInit() {
    this.fightService.getFights().subscribe({
      next: (data) => {
        this.fights = data;
        console.log('Fights data:', this.fights);  // For debugging
      },
      error: (error) => {
        console.error('Error fetching fights:', error);
      }
    });
  }

  getOdds(fight: Fight, fighter: string, bookmaker: string): string {
    return fight.odds[fighter]?.[bookmaker] || 'N/A';
  }
}

