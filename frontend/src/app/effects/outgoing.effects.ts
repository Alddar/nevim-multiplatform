import {Injectable} from '@angular/core'
import {Actions, createEffect, ofType} from '@ngrx/effects'
import {exhaust, tap} from 'rxjs/operators'
import {WebsocketService} from '../services/websocket.service'
import {CreateLobbyMessage, JoinLobbyMessage, NameMessage} from '../messages/outgoing/lobby'
import {createLobby, joinLobby, sendName} from '../actions/lobby.actions'
import {NameDTO} from '../dto/server'

@Injectable()
export class OutgoingEffects {
  sendName$ = createEffect(() =>
      this.actions$.pipe(
        ofType(sendName),
        tap((action) => this.ws$.send(new NameMessage(new NameDTO(action.name)))),
      ),
    {dispatch: false},
  )

  createLobby$ = createEffect(() =>
      this.actions$.pipe(
        ofType(createLobby),
        tap((action) => this.ws$.send(new CreateLobbyMessage(action.createLobbyDTO))),
      ),
    {dispatch: false},
  )

  joinLobby$ = createEffect(() =>
    this.actions$.pipe(
      ofType(joinLobby),
      tap((action) => this.ws$.send(new JoinLobbyMessage(action.idDTO)))
    ),
    {dispatch: false},
  )

  constructor(
    private actions$: Actions,
    private ws$: WebsocketService,
  ) {}
}
