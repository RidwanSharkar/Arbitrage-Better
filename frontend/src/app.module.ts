import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router'; // <-- Import RouterModule

import { AppComponent } from './app/app.component';
import { routes } from './app/app.routes'; // <-- Import your routes
import { FightsComponent } from 'app/fights/fights.component';

@NgModule({
  declarations: [
    AppComponent,
    FightsComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes) 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
