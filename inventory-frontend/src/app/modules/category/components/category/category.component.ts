import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { CategoryService } from '../../../shared/services/category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.getCategories();
  }

  displayedColumns: string[] = ['id','name','description','actions'];
  dataSource = new MatTableDataSource<CategoryElement>();

  getCategories() {
    this.categoryService.getCategories()
        .subscribe( (data:any) => {
           console.log("respuesta categories", data);
           this.processCategoriesResponse(data);
        }, (error: any) => {
          console.log(error);
        })
  }

  processCategoriesResponse(resp: any) {
    const dataCategory: CategoryElement[] = [];
    if(resp.metadata[0].code == "00") {
      let listCategory = resp.categoryResponse.category;
      listCategory.forEach( (element: CategoryElement) => {
        console.log("element: => ",element);
        dataCategory.push(element);
      });
      this.dataSource = new MatTableDataSource<CategoryElement>(dataCategory);
    }
  }


}

export interface CategoryElement  {
  id: number;
  description: string;
  name: string
}
