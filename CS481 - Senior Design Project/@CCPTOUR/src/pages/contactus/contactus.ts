import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { FormGroup, FormBuilder} from '@angular/forms';

@IonicPage()
@Component({
  selector: 'page-contactus',
  templateUrl: 'contactus.html',
}) 
export class ContactusPage {

  csForm: FormGroup;

  constructor(public navCtrl: NavController, public navParams: NavParams, private formBuilder:FormBuilder) {
 
    this.csForm = this.formBuilder.group({
      name: [''],
      email: [''],
      message: ['']
    });
  }

}
