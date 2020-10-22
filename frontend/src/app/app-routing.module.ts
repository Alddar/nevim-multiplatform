import {NgModule} from '@angular/core'
import {Routes, RouterModule} from '@angular/router'
import {LobbyListComponent} from './lobby/lobby-list.component'
import {NameComponent} from './name/name.component'

const routes: Routes = [
  {path: '', component: NameComponent, pathMatch: 'full'},
  {path: 'lobby-list', component: LobbyListComponent},
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
