import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class App {
  protected readonly title = signal('NutikasRestoran-web');

  constructor(private router: Router) {}

    public goToLauadView(){
      this.router.navigate(['/'])
    }
    
}
