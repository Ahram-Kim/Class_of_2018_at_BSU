import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

/**
 * Generated class for the CcpbuildingPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-ccpbuilding',
  templateUrl: 'ccpbuilding.html',
})
export class CcpbuildingPage {

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  images = ['CityCenterPlaza1.jpg', 'CityCenterPlaza2.jpg',
            '221-2.jpg', '221-4.jpg', '240reduced.jpg', '242reduced.jpg',
            '243-1.jpg', '352-1.jpg', '352-1altsetup.jpg', '352-2.jpg',
            '368-1.jpg', '368-2.jpg'];

  ionViewDidLoad() {
    console.log('ionViewDidLoad CcpbuildingPage');
  }

}
