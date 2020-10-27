import {Action, createReducer, on} from '@ngrx/store'
import {updateLobbyList} from '../actions/lobby.actions'

export interface LobbyListState {
  lobbies: {
    id: string,
    name: string
    maxPlayers: number,
    players: number,
  }[]
}

export const initialState: LobbyListState = {
  lobbies: []
}

const lobbyListReducer = createReducer(
  initialState,
  on(updateLobbyList, (state, {lobbyListDTO}) => ({...state, lobbies: lobbyListDTO.lobbies}))
)

export function reducer(state: LobbyListState | undefined, action: Action): LobbyListState {
  return lobbyListReducer(state, action)
}
