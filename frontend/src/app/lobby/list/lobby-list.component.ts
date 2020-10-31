import {Component, OnInit, ViewChild} from '@angular/core'
import {Store} from '@ngrx/store'
import {Observable} from 'rxjs'
import {faPlus, faUserCircle} from '@fortawesome/free-solid-svg-icons'
import {ModalComponent} from '../../shared/components/modal/modal.component'
import {PlayerState} from '../../reducers/player.reducer'
import {AppState} from '../../reducers/app.reducer'
import {selectPlayer} from '../../selectors/player.selector'
import {LobbyListState} from '../../reducers/lobby-list.reducer'
import {selectLobbyList} from '../../selectors/lobby-list.selector'
import {joinLobby} from '../../actions/lobby.actions'
import {IdDTO} from '../../dto/server'
import {take, tap} from 'rxjs/operators'

@Component({
  selector: 'app-lobby-list',
  templateUrl: './lobby-list.component.html',
})
export class LobbyListComponent implements OnInit {
  player$: Observable<PlayerState>
  lobbyList$: Observable<LobbyListState>

  @ViewChild('createLobbyModal')
  createLobbyModal: ModalComponent

  @ViewChild('userModal')
  userModal: ModalComponent

  faUserCircle = faUserCircle
  faPlus = faPlus

  constructor(private store: Store<AppState>) {
  }

  lobbyJoin(id: string): void {
    this.lobbyList$.pipe(take(1)).subscribe((lobbyList) => {
        lobbyList.lobbies.forEach((lobby) => {
          if (lobby.maxPlayers > lobby.players) {
            this.store.dispatch(joinLobby({idDTO: new IdDTO(id)}))
          }
        })
      },
    )
  }

  ngOnInit(): void {
    this.player$ = this.store.select(selectPlayer)
    this.lobbyList$ = this.store.select(selectLobbyList)
  }
}
