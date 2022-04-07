import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'blog-item-details',
  templateUrl: './blog-item-details.component.html',
  styleUrls: ['./blog-item-details.component.scss']
})
export class BlogItemDetailsComponent implements OnInit {

  public image: string = 'http://osnews.pl/wp-content/uploads/2016/06/it-grafika.jpg';
  public text: string = 'Tytuł';


  constructor() { }

  ngOnInit(): void {
  }

}
