import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { FacultyPage } from './faculty';

@NgModule({
  declarations: [
    FacultyPage,
  ],
  imports: [
    IonicPageModule.forChild(FacultyPage),
  ],
})
export class FacultyPageModule {}
