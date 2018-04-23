import {Component} from '@angular/core';
import {NavController} from 'ionic-angular';
import {HttpClient} from "@angular/common/http";
import {Movie} from "../../interfaces/Movie";
import {DetailPage} from "../details/details";
import {AlertController} from "ionic-angular";

@Component({
  selector: 'page-search',
  templateUrl: 'search.html'
})
export class SearchPage {
  movieData: string;

  searchQuery: string;

  doSearch(): void {
    let movieJson = this.httpClient.get('https://www.omdbapi.com/?apikey=d6c74096&plot=short&r=json&t=' + this.searchQuery);
    movieJson.subscribe(data => {
      let movie: Movie = <Movie>data;
      if ((movie.Response == 'True') && (this.searchQuery != null)) {
        this.movieData = "Title: " + movie.Title + "\n" +
          "Year: " + movie.Year + "\n" +
          "Director: " + movie.Director + "\n" +
          "Plot: " + movie.Plot;
        this.navCtrl.push(DetailPage)
      } else {
        let errorAlert = this.alertCtrl.create({
          title: 'Error',
          subTitle: movie.Error,
          buttons: ['Try Again']
        });
        if (this.searchQuery == null) {
          errorAlert.setSubTitle("Please specify a movie to search for");
        }
        errorAlert.present();

      }
    })
  }

  public getMovieData(): string {
    return this.movieData;
  }

  constructor(public navCtrl: NavController, public httpClient: HttpClient, private alertCtrl: AlertController) {

  }


}
