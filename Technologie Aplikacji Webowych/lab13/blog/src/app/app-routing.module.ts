import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BlogItemDetailsComponent} from "./components/blog-item-details/blog-item-details.component";
import {BlogHomeComponent} from "./components/blog-home/blog-home.component";
import {HomeComponent} from "./components/home/home.component";
import {AuthGuard} from "./services/auth.guard";
import {LoginComponent} from "./components/login/login.component";
import {SignupComponent} from "./components/signup/signup.component";
import {AddPostComponent} from "./components/add-post/add-post.component";

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'signup',
    component: SignupComponent
  },
  {
    path: 'blog',
    component: BlogHomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'blog/post/create',
    component: AddPostComponent,
    canActivate: [AuthGuard]
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
