import {Action, createReducer, on} from '@ngrx/store'
import {sendName, setId} from '../actions/lobby.actions'

export interface PlayerState {
  name: string | null,
  id: string | null
}

export const initialState: PlayerState = {
  name: null,
  id: null,
}

const playerReducer = createReducer(
  initialState,
  on(sendName, (state, {name}) => ({...state, name})),
  on(setId, (state, {idDTO}) => ({...state, id: idDTO.id})),
)

export function reducer(state: PlayerState | undefined, action: Action): PlayerState {
  return playerReducer(state, action)
}
