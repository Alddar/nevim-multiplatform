import {Injectable} from '@angular/core'
import {Actions, createEffect, ofType} from '@ngrx/effects'
import {tap} from 'rxjs/operators'
import {WebsocketService} from '../services/websocket.service'
import {Router} from '@angular/router'
import {lobbyId, setId, updateLobby} from '../actions/lobby.actions'

@Injectable()
export class NavigationEffects {
  toLobbyList$ = createEffect(() =>
      this.actions$.pipe(
        ofType(setId),
        tap(() => this.router.navigate(['lobby']))
      ),
    { dispatch: false }
  )

  toLobby$ = createEffect(() => this.actions$.pipe(
    ofType(lobbyId),
    tap((action) => {
      this.router.navigate(['lobby', action.idDTO.id])
    })
  ), {dispatch: false})

  joinLobby$ = createEffect(() => this.actions$.pipe(
    ofType(updateLobby),
    tap((action) => {
      this.router.navigate(['lobby', action.lobbyDTO.lobby.id])
    })),
    {dispatch: false}
  )

  constructor(
    private actions$: Actions,
    private ws$: WebsocketService,
    private router: Router,
  ) {}
}
