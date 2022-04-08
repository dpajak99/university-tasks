import { Component, OnInit } from '@angular/core';
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.scss']
})
export class AddPostComponent implements OnInit {

  public post = {
    title: '',
    image: '',
    text: '',
  };


  constructor(private dataService: DataService, public router: Router) { }

  ngOnInit(): void {
  }

  create() {
    this.dataService.create(this.post).subscribe((result) => {
      alert('Dodano post');
      console.log(result);
      return result;
    });

  }
}
