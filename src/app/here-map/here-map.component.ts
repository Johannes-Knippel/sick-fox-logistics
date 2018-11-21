import { Component, OnInit, ViewChild, ElementRef, Input } from '@angular/core';
import {isBoolean} from "util";

declare var H: any;

@Component({
  selector: 'here-map',
  templateUrl: './here-map.component.html',
  styleUrls: ['./here-map.component.scss']
})
export class HereMapComponent implements OnInit {

  @ViewChild("map")
  public mapElement: ElementRef;

  @Input()
  public appId: any;

  @Input()
  public appCode: any;

  @Input()
  public lat: any;

  @Input()
  public lng: any;

  @Input()
  public width: any;

  @Input()
  public height: any;

  private platform: any;
  private map: any;

  private ui: any;
  private search: any;

  public positionsLat: any[];
  public positionsLng: any[];
  public searchMarkers = new Array();
  private isEmpty: boolean;
  public sensor: any;

  public constructor() {
  }

  /*
  * Initialization
  * */
  public ngOnInit() {
    this.platform = new H.service.Platform({
      "app_id": this.appId,
      "app_code": this.appCode
    });
    this.search = new H.places.Search(this.platform.getPlacesService());
  }

  public ngAfterViewInit() {
    let defaultLayers = this.platform.createDefaultLayers();
    this.map = new H.Map(
      this.mapElement.nativeElement,
      defaultLayers.normal.map,
      {
        zoom: 10,
        center: {lat: this.lat, lng: this.lng}
      }
    );
    let behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(this.map));
    this.ui = H.ui.UI.createDefault(this.map, defaultLayers);
  }

  /*
  * Generate Search results and drop Markers at answered positions from API
  * Search request is only for the preset region in app.component.ts
  * */
  public places(query: string) {
    this.map.removeObjects(this.map.getObjects());
    this.search.request({"q": query, "at": this.lat + "," + this.lng}, {}, data => {
      for (let i = 0; i < data.results.items.length; i++) {
        this.dropMarker({
          "lat": data.results.items[i].position[0],
          "lng": data.results.items[i].position[1]
        }, data.results.items[i]);
        console.log("lat: " + data.results.items[i].position[0]);
      }
    }, error => {
      console.error(error);
    });
  }

  /*
  * Example function for random positions eg. the senseit Sensor
  * */
  public positionSens(sens0x: string) {
    if (sens0x == "sens01") {
      console.log("Button for Sensit1 has been clicked");
      this.sensor = "Sensit01";
      this.positionsLat = [48.1514877, 48.1862590, 48.2079030, 48.2555090, 48.2901800];
      this.positionsLng = [11.5525109, 11.5516490, 11.3917690, 11.3039110, 11.2482980];
      for (let i = 0; i < this.positionsLng.length; i++) {
        this.dropMarker({"lat": this.positionsLat[i], "lng": this.positionsLng[i]}, this.sensor);
      }
    }

    if (sens0x == "sens02") {
      console.log("Button for Sensit2 has been clicked");
      this.sensor = "Sensit02";
      this.positionsLat = [48.211939, 49.221648, 50.196765, 51.012396, 51.217197, 52.060983, 53.897384];
      this.positionsLng = [11.397202, 10.238455, 8.247169, 3.220075, 0.819624, -0.570163, -1.405086];
      for (let i = 0; i < this.positionsLng.length; i++) {
        this.dropMarker({"lat": this.positionsLat[i], "lng": this.positionsLng[i]}, this.sensor);
      }
    }

  }


  /*
  * Generate the actual Marker
  * */
  private dropMarker(coordinates: any, data: any) {
    let marker = new H.map.Marker(coordinates);
    marker.setData("<p>" + data.title + "<br>" + data.vicinity + "</p>");
    marker.addEventListener('tap', event => {
      let bubble = new H.ui.InfoBubble(event.target.getPosition(), {
        content: event.target.getData()
      });
      this.ui.addBubble(bubble);
    }, false);
    this.map.addObject(marker);
    this.searchMarkers.push(marker);
  }

  /*
  * Delete markers from map
  * */
  private deleteMarkers() {
    this.map.removeObjects(this.searchMarkers);
    console.log(this.searchMarkers);
    //empty the array
    while (this.searchMarkers.length >= 1) {
      this.searchMarkers.pop();
      console.log(this.searchMarkers);
    }

    console.log(this.searchMarkers);
  }
}

