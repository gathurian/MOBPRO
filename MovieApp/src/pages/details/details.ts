import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { SearchPage} from "../search/search";

@Component({
  selector: 'page-details',
  templateUrl: 'details.html'
})
export class DetailPage {



  constructor(public navCtrl: NavController) {
  }

}
