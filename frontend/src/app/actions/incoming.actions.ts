import {createAction, props} from '@ngrx/store'
import {IdDTO} from '../types/multiplatform-shared'

export const setId = createAction('[ INCOMING ] Set ID', props<{idDTO: IdDTO}>())
