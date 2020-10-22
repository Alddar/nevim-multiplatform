import {IncomingMessage} from './common'
import {IdDTO} from '../../types/multiplatform-shared'

export const IN_ID = 'ID_MESSAGE'

export class IdMessage extends IncomingMessage {
  constructor(public payload: IdDTO) {
    super(IN_ID, payload)
  }
}

