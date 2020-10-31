import {Component, OnInit} from '@angular/core'
import {Store} from '@ngrx/store'
import {AppState} from './reducers/app.reducer'
import {Router} from '@angular/router'
import {selectPlayer} from './selectors/player.selector'
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
    this.store.select(selectPlayer).pipe(take(1)).subscribe((player) => {
      if (player.id == null) {
        this.router.navigate(['/'])
      }
    })
  }
}
