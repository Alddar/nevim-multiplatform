import { OutgoingMessage } from './common'
import {CreateLobbyDTO, IdDTO, NameDTO} from '../../dto/server'

export const OUT_NAME = 'NAME_MESSAGE'

export class NameMessage extends OutgoingMessage {
  constructor(public payload: NameDTO) {
    super(OUT_NAME, payload)
  }
}

export const OUT_CREATE_LOBBY = 'CREATE_LOBBY'

export class CreateLobbyMessage extends OutgoingMessage {
  constructor(public payload: CreateLobbyDTO) {
    super(OUT_CREATE_LOBBY, payload)
  }
}


export const OUT_JOIN_LOBBY = 'JOIN_LOBBY'

export class JoinLobbyMessage extends OutgoingMessage {
  constructor(public payload: IdDTO) {
    super(OUT_JOIN_LOBBY, payload)
  }
}

export const OUT_LEAVE_LOBBY = 'LEAVE_LOBBY'

export class LeaveLobbyMessage extends OutgoingMessage {
  constructor() {
    super(OUT_LEAVE_LOBBY, {})
  }
}
