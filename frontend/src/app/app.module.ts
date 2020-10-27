import {BrowserModule} from '@angular/platform-browser'
import {NgModule} from '@angular/core'
import {AppRoutingModule} from './app-routing.module'
import {AppComponent} from './app.component'
import {StoreModule} from '@ngrx/store'
import {NameComponent} from './name/name.component'
import {reducers} from './reducers/app.reducer'
import {StoreDevtoolsModule} from '@ngrx/store-devtools'
import {LobbyListComponent} from './lobby/list/lobby-list.component'
import {EffectsModule} from '@ngrx/effects'
import {OutgoingEffects} from './effects/outgoing.effects'
import {IncomingEffects} from './effects/incoming.effect'
import {NavigationEffects} from './effects/navigation.effects'
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome'
import {ModalComponent} from './shared/components/modal/modal.component'
import {ReactiveFormsModule} from '@angular/forms'
import {LobbyCreateComponent} from './lobby/create/lobby-create.component'
import { LobbyComponent } from './lobby/lobby.component'

@NgModule({
  declarations: [
    AppComponent,
    NameComponent,
    LobbyListComponent,
    ModalComponent,
    LobbyCreateComponent,
    LobbyComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    StoreModule.forRoot(reducers),
    StoreDevtoolsModule.instrument({
      maxAge: 25, // Retains last 25 states
    }),
    EffectsModule.forRoot([
      OutgoingEffects,
      IncomingEffects,
      NavigationEffects,
    ]),
    FontAwesomeModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
}
