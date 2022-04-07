import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BlogComponent} from "./components/blog/blog.component";
import {BlogItemDetailsComponent} from "./components/blog-item-details/blog-item-details.component";

const routes: Routes = [
  {
    path: '',
    component: BlogComponent,
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
export class AppRoutingModule { }
