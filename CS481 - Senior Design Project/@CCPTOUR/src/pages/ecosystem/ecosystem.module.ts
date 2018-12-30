import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { EcosystemPage } from './ecosystem';

@NgModule({
  declarations: [
    EcosystemPage,
  ],
  imports: [
    IonicPageModule.forChild(EcosystemPage),
  ],
})
export class EcosystemPageModule {}
