import { OutgoingMessage } from './common'
import {CreateLobbyDTO, NameDTO} from '../../types/multiplatform-shared'

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
