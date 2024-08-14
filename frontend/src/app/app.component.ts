// src/app/app.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { FightService } from './services/fight.service';
import { Fight } from '../models/fight.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true,
  imports: [CommonModule, RouterOutlet]
})

export class AppComponent implements OnInit {
  title = 'Arbitrager';
  fights: Fight[] = [];
  bookmakers: string[] = ['DraftKings', 'BetMGM', 'Caesars', 'BetRivers', 'FanDuel', 'BetWay'];

  constructor(private fightService: FightService) {}

  ngOnInit() {
    this.fightService.getFights().subscribe(data => {
      this.fights = data;
    });
  }
  
  getOdds(fight: Fight, fighter: string, bookmaker: string): string {
    return fight.odds[fighter]?.[bookmaker] || 'N/A';
  }
}