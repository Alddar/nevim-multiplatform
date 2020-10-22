import {createAction, props} from '@ngrx/store'
import {CreateLobbyDTO, IdDTO} from '../types/multiplatform-shared'

export const sendName = createAction('[ OUTGOING ] To Lobby', props<{name: string}>())
export const createLobby = createAction('[ OUTGOING ] Create Lobby', props<{createLobbyDTO: CreateLobbyDTO}>())
