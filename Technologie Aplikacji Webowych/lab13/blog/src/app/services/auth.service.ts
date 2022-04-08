import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {JwtHelperService} from "@auth0/angular-jwt";
import {map} from 'rxjs/operators';
import {Token} from "../models/token";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = 'http://localhost:3000/api';

  constructor(private http: HttpClient) {
  }

  authenticate(credentials: any) {

    return this.http.post(this.url + '/user/auth', {
      login: credentials.login,
      password: credentials.password
    }).pipe(
      map((result: Token | any) => {
        if (result && result.token) {
          localStorage.setItem('token', result.token);
          return true;
        }
        return false;
      })
    );
  }

  createOrUpdate(credentials: any) {
    console.log(credentials)
    return this.http.post(this.url + '/user/create', credentials);
  }

  logout() {
    return this.http.delete(this.url + '/user/logout/' + this.currentUser.userId)
      .pipe(
        map(() => {
          localStorage.removeItem('token');
        })
      );
  }

  isLoggedIn() {
    const jwtHelper = new JwtHelperService();
    const token = localStorage.getItem('token');
    if (!token) {
      return false;
    }
    return !(jwtHelper.isTokenExpired(token));
  }

  get currentUser() {
    const token = this.getToken();
    if (!token) {
      return null;
    }

    return new JwtHelperService().decodeToken(token);
  }

  getToken() {
    return localStorage.getItem('token');
  }
}
