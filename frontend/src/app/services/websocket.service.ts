import {Injectable} from '@angular/core'
import {WebSocketSubject} from 'rxjs/internal-compatibility'
import {Observable} from 'rxjs'
import {environment} from '../../environments/environment'
import {webSocket} from 'rxjs/webSocket'
import {Action} from '@ngrx/store'
import {map, shareReplay, switchMap} from 'rxjs/operators'
import {setId} from '../actions/incoming.actions'
import {IncomingMessage} from '../messages/incoming/common'
import {IN_ID} from '../messages/incoming/lobby'

export const WS_ENDPOINT = environment.wsEndpoint

@Injectable({
  providedIn: 'root',
})
export class WebsocketService {
  socket$: WebSocketSubject<any>

  getActions(): Observable<Action> {
    if (!this.socket$) {
      this.socket$ = webSocket(WS_ENDPOINT)
    }

    return this.socket$.pipe(
      map((message: IncomingMessage) => {
        switch (message.type) {
          case IN_ID:
            return setId({idDTO: message.payload})
        }
      }),
    )
  }

  send(message): void {
    console.log(message)
    this.socket$.next(message)
  }
}
