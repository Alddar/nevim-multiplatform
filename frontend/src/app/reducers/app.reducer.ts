import * as fromPlayer from './local-player.reducer'
import {ActionReducerMap} from '@ngrx/store'

export interface AppState {
  player: fromPlayer.LocalPlayerState
}

export const reducers: ActionReducerMap<AppState> = {
  player: fromPlayer.reducer,
}
