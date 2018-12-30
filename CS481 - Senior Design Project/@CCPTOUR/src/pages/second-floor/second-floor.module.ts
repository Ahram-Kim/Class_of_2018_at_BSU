import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { SecondFloorPage } from './second-floor';

@NgModule({
  declarations: [
    SecondFloorPage,
  ],
  imports: [
    IonicPageModule.forChild(SecondFloorPage),
  ],
})
export class SecondFloorPageModule {}
