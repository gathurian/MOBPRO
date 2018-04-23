import {Component, forwardRef, Inject} from '@angular/core';
import { NavController } from 'ionic-angular';
import { SearchPage} from "../search/search";
import {Movie} from "../../interfaces/Movie";
import {HttpClient} from "@angular/common/http";



@Component({
  selector: 'page-details',
  templateUrl: 'details.html'
})
export class DetailPage {

  data:string;

  constructor(public navCtrl: NavController, @Inject(forwardRef(() => SearchPage)) public srcPage:
  SearchPage) {
  }

}
