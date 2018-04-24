import {Component} from '@angular/core';
import {NavController, NavParams} from 'ionic-angular';
import {Movie} from "../../interfaces/Movie";
import {HttpClient} from "@angular/common/http";
import { default as movie1 } from '../../assets/movies/1.json';
import { default as movie2 } from '../../assets/movies/2.json';
import { default as movie3 } from '../../assets/movies/3.json';
import { default as movie4 } from '../../assets/movies/4.json';
import { default as movie5 } from '../../assets/movies/5.json';
import {DetailPage} from "../details/details";


@Component({
  selector: 'page-list',
  templateUrl: 'list.html'
})
export class ListPage {
  selectedItem: Movie;
  movies: Array<Movie>;

  constructor(public navCtrl: NavController, public navParams: NavParams, public httpClient: HttpClient) {
    // If we navigated to this page, we will have an item available as a nav param
    this.selectedItem = navParams.get('item');

    this.movies = [];
    /*for (let i = 1; i <= 5; i++) {
      let movieJson = this.httpClient.get('../../assets/movies/'+i+ '.json');
      movieJson.subscribe(data => {
        let movie: Movie = <Movie>data;
        this.movies.push(movie);
        console.log(this.movies.length);
      });
    }*/
    this.movies.push(<Movie>movie2);
    this.movies.push(<Movie>movie3);
    this.movies.push(<Movie>movie1);
    this.movies.push(<Movie>movie5);
    this.movies.push(<Movie>movie4);

  }

  private getNumOfMovies(){
    return this.movies.length;
  }

  itemTapped(event, movie:Movie) {
    // That's right, we're pushing to ourselves!
    this.navCtrl.push(DetailPage, movie);
  }
}
