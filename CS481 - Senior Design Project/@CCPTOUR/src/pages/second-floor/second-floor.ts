import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

@IonicPage()
@Component({
  selector: 'page-second-floor',
  templateUrl: 'second-floor.html',
})
export class SecondFloorPage {

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad SecondFloorPage');
  }

  openFaculty() {
    this.navCtrl.push('FacultyPage');
  }
}
