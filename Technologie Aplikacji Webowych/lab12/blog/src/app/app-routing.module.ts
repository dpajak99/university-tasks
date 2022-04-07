import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BlogItemDetailsComponent} from "./components/blog-item-details/blog-item-details.component";
import {BlogHomeComponent} from "./components/blog-home/blog-home.component";

const routes: Routes = [
  {
    path: '',
    component: BlogHomeComponent,
  },
  {
    path: 'blog/detail/:id',
    component: BlogItemDetailsComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
