import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

@Component({
  selector: 'page-about',
  templateUrl: 'about.html'
})
export class AboutPage {

  constructor(public navCtrl: NavController) {

  }

  openNewstudents() {
    this.navCtrl.push('NewstudentsPage');
  }

  openCcpbuilding() {
    this.navCtrl.push('CcpbuildingPage');
  }

  openCsdepart() {
    this.navCtrl.push('CsdepartmentPage');
  }
  
  openEcosystems() {
    this.navCtrl.push('EcosystemPage');
  }
  
  openFaculty() {
    this.navCtrl.push('FacultyPage');
  }
  
  openContactus() {
    this.navCtrl.push('ContactusPage');
  }

}
