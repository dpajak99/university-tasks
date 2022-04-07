import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'blog-item',
  templateUrl: './blog-item.component.html',
  styleUrls: ['./blog-item.component.scss']
})
export class BlogItemComponent implements OnInit {

  @Input() image?: string;
  @Input() text?: string;

  constructor() { }

  ngOnInit(): void {
  }

}
