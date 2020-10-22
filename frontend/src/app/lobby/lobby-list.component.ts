import {Component, OnInit, ViewChild} from '@angular/core'
import {Store} from '@ngrx/store'
import {AppState} from '../reducers/app.reducer'
import {Observable} from 'rxjs'
import {LocalPlayerState} from '../reducers/local-player.reducer'
import {selectLocalPlayer, selectLocalPlayerName} from '../selectors/local-player.selector'
import {ModalComponent} from '../shared/components/modal/modal.component'
import {faPlus, faUserCircle} from '@fortawesome/free-solid-svg-icons'
import {take} from 'rxjs/operators'
import {sendName} from '../actions/outgoing.actions'

@Component({
  selector: 'app-lobby-list',
  templateUrl: './lobby-list.component.html',
})
export class LobbyListComponent implements OnInit {
  localPlayer$: Observable<LocalPlayerState>

  @ViewChild('createLobbyModal')
  createLobbyModal: ModalComponent

  @ViewChild('userModal')
  userModal: ModalComponent

  faUserCircle = faUserCircle
  faPlus = faPlus

  constructor(private store: Store<AppState>) {
  }

  ngOnInit(): void {
    this.localPlayer$ = this.store.select(selectLocalPlayer)
  }
}
