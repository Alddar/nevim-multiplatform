import {Injectable} from '@angular/core'
import {Actions, createEffect, ofType} from '@ngrx/effects'
import {tap} from 'rxjs/operators'
import {WebsocketService} from '../services/websocket.service'
import {setId} from '../actions/incoming.actions'
import {Router} from '@angular/router'

@Injectable()
export class NavigationEffects {
  toLobbyList$ = createEffect(() =>
      this.actions$.pipe(
        ofType(setId),
        tap(() => this.router.navigate(['lobby-list']))
      ),
    { dispatch: false }
  )

  constructor(
    private actions$: Actions,
    private ws$: WebsocketService,
    private router: Router,
  ) {}
}
