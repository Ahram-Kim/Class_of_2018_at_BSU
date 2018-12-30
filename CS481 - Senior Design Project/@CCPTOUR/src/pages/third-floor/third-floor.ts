import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

/**
 * Generated class for the ThirdFloorPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-third-floor',
  templateUrl: 'third-floor.html',
})
export class ThirdFloorPage {

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  } 

  ionViewDidLoad() {
    console.log('ionViewDidLoad ThirdFloorPage');
  }

  openFaculty() {
    this.navCtrl.push('FacultyPage');
  }

}
