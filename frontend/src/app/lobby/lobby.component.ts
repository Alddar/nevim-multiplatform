import {Component, Input, OnInit} from '@angular/core'
import {ActivatedRoute} from '@angular/router'
import {faCheck, faTimes} from '@fortawesome/free-solid-svg-icons'
import {Observable} from 'rxjs'
import {selectLobby} from '../selectors/lobby.selector'
import {Store} from '@ngrx/store'
import {AppState} from '../reducers/app.reducer'
import {LobbyDTO} from '../dto/server'
import {leaveLobby, ready} from '../actions/lobby.actions'
import {PlayerState} from '../reducers/player.reducer'
import {selectPlayer} from '../selectors/player.selector'

@Component({
  selector: 'app-lobby',
  templateUrl: './lobby.component.html',
})
export class LobbyComponent implements OnInit {
  player$: Observable<PlayerState>
  lobby$: Observable<LobbyDTO>

  faCheck = faCheck
  faTimes = faTimes

  constructor(private route: ActivatedRoute,
              private store: Store<AppState>) {
  }

  ngOnInit(): void {
    this.player$ = this.store.select(selectPlayer)
    this.lobby$ = this.store.select(selectLobby)
  }

  ready(): void {
    this.store.dispatch(ready())
  }

  leave(): void {
    this.store.dispatch(leaveLobby())
  }
}
