import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/pages/dashboard.component';
import { HomeComponent } from './dashboard/components/home/home.component';
import { RouterModule } from '@angular/router';
import { SharedModule } from './shared/shared.module';



@NgModule({
  declarations: [
    DashboardComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    SharedModule
  ]
})
export class DashboardModule { }
