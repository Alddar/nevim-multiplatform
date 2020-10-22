import {Component, OnInit} from '@angular/core'
import {Store} from '@ngrx/store'
import {AppState} from '../reducers/app.reducer'
import {Observable} from 'rxjs'
import {selectLocalPlayer, selectLocalPlayerName} from '../selectors/local-player.selector'
import {setName} from '../actions/local-player.actions'
import {LocalPlayerState} from '../reducers/local-player.reducer'
import {WebsocketService} from '../services/websocket.service'
import {first, take} from 'rxjs/operators'
import {sendName} from '../actions/outgoing.actions'

@Component({
  selector: 'app-name',
  templateUrl: './name.component.html'
})
export class NameComponent implements OnInit {

  localPlayer$: Observable<LocalPlayerState>

  constructor(private store: Store<AppState>) {
  }

  ngOnInit(): void {
    this.localPlayer$ = this.store.select(selectLocalPlayer)
  }

  nameChanged(e: KeyboardEvent): void {
    this.store.dispatch(setName({name: (e.target as HTMLInputElement).value}))
  }

  continue(): void {
    this.store.select(selectLocalPlayerName).pipe(take(1)).subscribe((name) => {
      this.store.dispatch(sendName({name}))
    })
  }
}
