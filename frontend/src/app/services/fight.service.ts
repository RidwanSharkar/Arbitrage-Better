// src/app/services/fight.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Fight } from '../../models/fight.model'; 

/*===========================================================================*/

@Injectable({
  providedIn: 'root'
})
export class FightService {
  private apiUrl = 'http://localhost:8080/scrape-fights';

  constructor(private http: HttpClient) {}

  getFights(): Observable<Fight[]> {
    return this.http.get<Fight[]>(this.apiUrl);
  }
}
