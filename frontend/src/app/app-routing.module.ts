import {NgModule} from '@angular/core'
import {Routes, RouterModule} from '@angular/router'
import {LobbyListComponent} from './lobby/list/lobby-list.component'
import {LobbyComponent} from './lobby/lobby.component'
import {NameComponent} from './name/name.component'

const routes: Routes = [
  {path: '', component: NameComponent, pathMatch: 'full'},
  {path: 'lobby/:id', component: LobbyComponent},
  {path: 'lobby', component: LobbyListComponent},
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
