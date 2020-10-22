import {Injectable} from '@angular/core'
import {Actions, createEffect, ofType} from '@ngrx/effects'
import {exhaust, tap} from 'rxjs/operators'
import {WebsocketService} from '../services/websocket.service'
import {CreateLobbyMessage, NameMessage} from '../messages/outgoing/lobby'
import {NameDTO} from '../types/multiplatform-shared'
import {createLobby, sendName} from '../actions/outgoing.actions'

@Injectable()
export class OutgoingEffects {
  sendName$ = createEffect(() =>
    this.actions$.pipe(
      ofType(sendName),
      tap((action) => this.ws$.send(new NameMessage(new NameDTO(action.name))))
    ),
    { dispatch: false }
  )

  createLobby$ = createEffect(() =>
    this.actions$.pipe(
      ofType(createLobby),
      tap((action) => this.ws$.send(new CreateLobbyMessage(action.createLobbyDTO)))
    ),
    { dispatch: false }
  )

  constructor(
    private actions$: Actions,
    private ws$: WebsocketService,
  ) {}
}
