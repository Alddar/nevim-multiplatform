import {Component, OnInit} from '@angular/core'
import {WebsocketService} from './services/websocket.service'
import {Store} from '@ngrx/store'
import {AppState} from './reducers/app.reducer'
import {Router} from '@angular/router'
import {selectLocalPlayerName} from './selectors/local-player.selector'
import {take, tap} from 'rxjs/operators'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass'],
})
export class AppComponent implements OnInit {
  title = 'frontend'

  constructor(private store: Store<AppState>,
              private router: Router) {
  }

  ngOnInit(): void {
    this.store.select(selectLocalPlayerName).pipe(take(1)).subscribe((name) => {
      if (name == null) {
        this.router.navigate(['/'])
      }
    })
  }
}
