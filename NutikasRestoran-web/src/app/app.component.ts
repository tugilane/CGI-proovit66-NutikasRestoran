import { Component, signal } from '@angular/core';
import { laudListComponent } from "./features/lauad/list/laud-list.component";
import { OtsinugPaneelComponent } from './features/otsinguPaneel/paneel/otsinguPaneel.component';
import { HeaderComponent } from './layout/header.component';
import { SoovitusPaneelComponent } from './features/soovitusPaneel/paneel/soovitusPaneel.component';
import { FooterComponent } from './layout/footer.component'

@Component({
  selector: 'app-root',
  imports: [
    HeaderComponent, 
    FooterComponent,
    laudListComponent, 
    OtsinugPaneelComponent, 
    SoovitusPaneelComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  protected readonly title = signal('NutikasRestoran-web');   
}
