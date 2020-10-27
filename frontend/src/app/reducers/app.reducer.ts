import * as fromPlayer from './player.reducer'
import * as fromLobbyList from './lobby-list.reducer'
import * as fromLobby from './lobby.reducer'
import {ActionReducerMap} from '@ngrx/store'
import {LobbyDTO} from '../dto/server'

export interface AppState {
  player: fromPlayer.PlayerState,
  lobbies: fromLobbyList.LobbyListState,
  lobby: LobbyDTO
}

export const reducers: ActionReducerMap<AppState> = {
  player: fromPlayer.reducer,
  lobbies: fromLobbyList.reducer,
  lobby: fromLobby.reducer,
}
