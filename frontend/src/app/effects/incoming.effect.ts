import { Injectable } from '@angular/core'
import { Actions, createEffect, ofType } from '@ngrx/effects'
import {WebsocketService} from '../services/websocket.service'

@Injectable()
export class IncomingEffects {
  init$ = createEffect(() => this.websocketService.getActions())

  constructor(
    private actions$: Actions,
    private websocketService: WebsocketService
  ) {}
}
