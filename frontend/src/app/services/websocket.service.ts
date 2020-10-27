import {Injectable} from '@angular/core'
import {WebSocketSubject} from 'rxjs/internal-compatibility'
import {Observable} from 'rxjs'
import {environment} from '../../environments/environment'
import {webSocket} from 'rxjs/webSocket'
import {Action} from '@ngrx/store'

export const WS_ENDPOINT = environment.wsEndpoint

@Injectable({
  providedIn: 'root',
})
export class WebsocketService {
  socket$: WebSocketSubject<any>

  getSocket(): Observable<Action> {
    if (!this.socket$) {
      this.socket$ = webSocket(WS_ENDPOINT)
    }

    this.socket$.subscribe((message) => console.log('INCOMING', message))

    return this.socket$
  }

  send(message): void {
    console.log('OUTGOING', message)
    this.socket$.next(message)
  }
}
