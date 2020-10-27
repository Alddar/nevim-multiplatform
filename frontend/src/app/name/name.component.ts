import {Component, OnInit} from '@angular/core'
import {Store} from '@ngrx/store'
import {AppState} from '../reducers/app.reducer'
import {sendName} from '../actions/lobby.actions'
import {FormBuilder, Validators} from '@angular/forms'

@Component({
  selector: 'app-name',
  templateUrl: './name.component.html'
})
export class NameComponent implements OnInit {
  playerForm = this.fb.group({
    name: ['', Validators.compose([
      Validators.required,
      Validators.maxLength(20),
    ])],
  })

  constructor(private store: Store<AppState>,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const name = this.playerForm.get('name').value
    this.store.dispatch(sendName({name}))
  }
}
