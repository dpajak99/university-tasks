import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'blog-item-image',
  templateUrl: './blog-item-image.component.html',
  styleUrls: ['./blog-item-image.component.scss']
})
export class BlogItemImageComponent implements OnInit {

  @Input() image?: string;

  constructor() { }

  ngOnInit(): void {
  }

}
