// src/app/fights/fight.components.ts


import { Component, OnInit } from '@angular/core';
import { FightService } from '../services/fight.service';
import { Fight } from '../../models/fight.model';

@Component({
  selector: 'app-fights',
  templateUrl: './fights.component.html',
  styleUrls: ['./fights.component.css']
})

export class FightsComponent implements OnInit {
  fights: Fight[] = [];  

  constructor(private fightService: FightService) {}

  ngOnInit() {
    this.fightService.getFights().subscribe(data => {
      this.fights = data;
    });
  }
}

