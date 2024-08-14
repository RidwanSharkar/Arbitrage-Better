// src/app/app.routes.ts
import { Routes } from '@angular/router';
import { FightsComponent } from './fights/fights.component';

export const routes: Routes = [
  { path: '', redirectTo: '/fights', pathMatch: 'full' },
  { path: 'fights', component: FightsComponent }
];