import {createAction, props} from '@ngrx/store'

export const setName = createAction('[ LOCAL PLAYER ] Set Name', props<{name: string}>())
