import {Action, createReducer, on} from '@ngrx/store'
import {updateLobby} from '../actions/lobby.actions'
import {LobbyDTO} from '../dto/server'

export const initialState: LobbyDTO = {
  lobby: null
}

const lobbyReducer = createReducer(
  initialState,
  on(updateLobby, (state, {lobbyDTO}) => ({...state, lobby: lobbyDTO.lobby}))
)

export function reducer(state: LobbyDTO | undefined, action: Action): LobbyDTO {
  return lobbyReducer(state, action)
}
