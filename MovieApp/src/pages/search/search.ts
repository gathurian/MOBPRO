import {Component} from '@angular/core';
import {NavController} from 'ionic-angular';
import {HttpClient} from "@angular/common/http";
import {Movie} from "../../interfaces/Movie";
import {DetailPage} from "../details/details";

@Component({
  selector: 'page-search',
  templateUrl: 'search.html'
})
export class SearchPage {

  searchQuery: string;
  movieData: string;

  doSearch(): void {
    let movieJson = this.httpClient.get('https://www.omdbapi.com/?apikey=d6c74096&plot=short&r=json&t=' + this.searchQuery);
    movieJson.subscribe(data => {
      let movie: Movie = <Movie>data;
      if (movie.Response == 'True') {
        this.movieData = "Title: " + movie.Title + "\n" +
          "Year: " + movie.Year + "\n" +
          "Director: " + movie.Director + "\n" +
          "Plot: " + movie.Plot;
        this.navCtrl.push(DetailPage)
      } else {
        alert(movie.Error);
      }
    })
  }

  constructor(public navCtrl: NavController, public httpClient: HttpClient) {

  }


}
