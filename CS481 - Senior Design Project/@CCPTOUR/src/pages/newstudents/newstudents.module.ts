import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { NewstudentsPage } from './newstudents';

@NgModule({
  declarations: [
    NewstudentsPage,
  ],
  imports: [
    IonicPageModule.forChild(NewstudentsPage),
  ],
})
export class NewstudentsPageModule {}
