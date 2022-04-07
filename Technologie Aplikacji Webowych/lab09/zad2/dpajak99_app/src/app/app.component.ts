import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'dpajak99_app';

  public counter:number = 0;

  add() {
    this.counter++;
  }

  remove() {
    this.counter--;
  }
}
