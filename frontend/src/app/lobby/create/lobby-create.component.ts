import {Component, OnInit} from '@angular/core'
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms'
import {selectLocalPlayerName} from '../../selectors/local-player.selector'
import {take} from 'rxjs/operators'
import {createLobby, sendName} from '../../actions/outgoing.actions'
import {Store} from '@ngrx/store'
import {AppState} from '../../reducers/app.reducer'
import {CreateLobbyDTO} from '../../types/multiplatform-shared'

@Component({
  selector: 'app-lobby-create',
  templateUrl: './lobby-create.component.html',
})
export class LobbyCreateComponent implements OnInit {
  lobbyForm = this.fb.group({
    name: ['', Validators.required],
    maxPlayers: [
      2,
      Validators.compose([
        Validators.min(2),
        Validators.max(8),
      ]),
    ],
  })

  constructor(private fb: FormBuilder,
              private store: Store<AppState>) {
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.store.dispatch(createLobby({
      createLobbyDTO: new CreateLobbyDTO(
        this.lobbyForm.get('name').value,
        this.lobbyForm.get('maxPlayers').value),
    }))
  }
}
