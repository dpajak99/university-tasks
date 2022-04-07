import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class DataService {

  private url = 'https://taw-posts.herokuapp.com';

    constructor(private http: HttpClient) {
}

getAll() {
  return this.http.get(this.url + '/api/posts');
}
}
