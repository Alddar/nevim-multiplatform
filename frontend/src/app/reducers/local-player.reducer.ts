import {Action, createReducer, on} from '@ngrx/store'
import {setName} from '../actions/local-player.actions'
import {ActionReducer} from '@ngrx/store/src/models'
import {setId} from '../actions/incoming.actions'

export const localPlayerFeatureKey = 'localPlayer'

export interface LocalPlayerState {
  name: string | null,
  id: string | null
}

export const initialState: LocalPlayerState = {
  name: null,
  id: null,
}

const localPlayerReducer = createReducer(
  initialState,
  on(setName, (state, {name}) => ({...state, name})),
  on(setId, (state, {idDTO}) => ({...state, id: idDTO.id})),
)

export function reducer(state: LocalPlayerState | undefined, action: Action): LocalPlayerState {
  return localPlayerReducer(state, action)
}
