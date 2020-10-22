import {createSelector} from '@ngrx/store'
import {AppState} from '../reducers/app.reducer'
import {LocalPlayerState} from '../reducers/local-player.reducer'

export const selectLocalPlayer = (state: AppState) => state.player

export const selectLocalPlayerName = createSelector(
  selectLocalPlayer,
  (state: LocalPlayerState) => state.name,
)
