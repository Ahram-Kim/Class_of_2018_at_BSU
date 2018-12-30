import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

@IonicPage()
@Component({
  selector: 'page-newstudents',
  templateUrl: 'newstudents.html',
})
export class NewstudentsPage {

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  openEcosystem() {
    this.navCtrl.push('EcosystemPage');
  }

  openLocation() {
    this.navCtrl.push('LocationPage');
  }

  openMap() {
    this.navCtrl.push('MapPage');
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad NewstudentsPage');
  }

}
