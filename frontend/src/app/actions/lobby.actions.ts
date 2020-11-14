import {createAction, props} from '@ngrx/store'
import {CreateLobbyDTO, IdDTO, LobbyDTO, LobbyListDTO} from '../dto/server'

export const sendName = createAction('[ OUTGOING ] To Lobby', props<{name: string}>())
export const createLobby = createAction('[ OUTGOING ] Create Lobby', props<{createLobbyDTO: CreateLobbyDTO}>())
export const joinLobby = createAction('[ OUTGOING ] Join Lobby', props<{idDTO: IdDTO}>())
export const leaveLobby = createAction('[ OUTGOING ] Leave Lobby')
export const ready = createAction('[ OUTGOING ] Ready')

export const setId = createAction('[ INCOMING ] Set ID', props<{idDTO: IdDTO}>())
export const updateLobbyList = createAction('[ INCOMING ] Update Lobby List', props<{lobbyListDTO: LobbyListDTO}>())
export const updateLobby = createAction('[ INCOMING ] Update lobby', props<{lobbyDTO: LobbyDTO}>())
