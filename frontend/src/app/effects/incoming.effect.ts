import {Injectable} from '@angular/core'
import {Actions, createEffect} from '@ngrx/effects'
import {WebsocketService} from '../services/websocket.service'
import {map} from 'rxjs/operators'
import {IncomingMessage} from '../messages/incoming/common'
import {IN_ID, IN_LOBBY_ID, IN_UPDATE_LOBBY, IN_UPDATE_LOBBY_LIST} from '../messages/incoming/lobby'
import {lobbyId, setId, updateLobby, updateLobbyList} from '../actions/lobby.actions'

@Injectable()
export class IncomingEffects {
  init$ = createEffect(() => this.websocketService.getSocket().pipe(
    map((message: IncomingMessage) => {
      switch (message.type) {
        case IN_ID:
          return setId({idDTO: message.payload})
        case IN_LOBBY_ID:
          return lobbyId({idDTO: message.payload})
        case IN_UPDATE_LOBBY_LIST:
          return updateLobbyList({lobbyListDTO: message.payload})
        case IN_UPDATE_LOBBY:
          return updateLobby({lobbyDTO: message.payload})
      }
    })))

  constructor(
    private actions$: Actions,
    private websocketService: WebsocketService,
  ) {
  }
}
