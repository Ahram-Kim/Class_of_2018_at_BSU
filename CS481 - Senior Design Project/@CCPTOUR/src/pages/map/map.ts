import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';


@Component({
  selector: 'page-map',
  templateUrl: 'map.html',
})
export class MapPage {

  // map: google.maps.Map;

  constructor(public navCtrl: NavController) {

  }

  open2ndFloor() {
    this.navCtrl.push('SecondFloorPage');
  }

  open3rdFloor() {
    this.navCtrl.push('ThirdFloorPage');
  }

}
