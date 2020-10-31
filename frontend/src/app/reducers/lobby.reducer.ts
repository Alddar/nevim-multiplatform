import {Action, createReducer, on} from '@ngrx/store'
import {leaveLobby, updateLobby} from '../actions/lobby.actions'
import {LobbyDTO} from '../dto/server'

export const initialState: LobbyDTO = {
  lobby: null
}

const lobbyReducer = createReducer(
  initialState,
  on(updateLobby, (state, {lobbyDTO}) => ({...state, lobby: lobbyDTO.lobby})),
  on(leaveLobby, (state) => ({...state, lobby: null}))
)

export function reducer(state: LobbyDTO | undefined, action: Action): LobbyDTO {
  return lobbyReducer(state, action)
}
