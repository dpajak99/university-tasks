import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {DataService} from '../../services/data.service';


@Component({
  selector: 'blog',
templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.scss']
})
export class BlogComponent implements OnInit {
// przed obiektem items

  @Input() filterText: string = '';

  public items$: any;

  constructor(private service: DataService) {
  }
  ngOnInit() {
    this.getAll();
  }

  getAll(){
    this.service.getAll().subscribe(response => {
      this.items$ = response;
    });
  }
}

