import {IncomingMessage} from './common'
import {IdDTO, LobbyDTO, LobbyListDTO} from '../../dto/server'

export const IN_ID = 'ID_MESSAGE'

export class IdMessage extends IncomingMessage {
  constructor(public payload: IdDTO) {
    super(IN_ID, payload)
  }
}

export const IN_LOBBY_ID = 'ID_LOBBY'

export class LobbyIdMessage extends IncomingMessage {
  constructor(public payload: IdDTO) {
    super(IN_LOBBY_ID, payload)
  }
}

export const IN_UPDATE_LOBBY_LIST = 'UPDATE_LOBBY_LIST'

export class UpdateLobbyListMessage extends IncomingMessage {
  constructor(public payload: LobbyListDTO) {
    super(IN_UPDATE_LOBBY_LIST, payload)
  }
}

export const IN_UPDATE_LOBBY = 'UPDATE_LOBBY'

export class UpdateLobbyMessage extends IncomingMessage {
  constructor(public payload: LobbyDTO) {
    super(IN_UPDATE_LOBBY, payload)
  }
}
