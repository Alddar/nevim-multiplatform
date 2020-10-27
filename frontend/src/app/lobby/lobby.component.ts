import {Component, Input, OnInit} from '@angular/core'
import {ActivatedRoute} from '@angular/router'
import {faCheck, faTimes} from '@fortawesome/free-solid-svg-icons'
import {Observable} from 'rxjs'
import {selectLobby} from '../selectors/lobby.selector'
import {Store} from '@ngrx/store'
import {AppState} from '../reducers/app.reducer'
import {LobbyDTO} from '../dto/server'

@Component({
  selector: 'app-lobby',
  templateUrl: './lobby.component.html',
})
export class LobbyComponent implements OnInit {
  lobby$: Observable<LobbyDTO>

  faCheck = faCheck
  faTimes = faTimes

  constructor(private route: ActivatedRoute,
              private store: Store<AppState>) {
  }

  ngOnInit(): void {
    this.lobby$ = this.store.select(selectLobby)
  }

  ready(): void {
    console.log('ready')
  }

  leave(): void {
    console.log('leave')
  }

}
