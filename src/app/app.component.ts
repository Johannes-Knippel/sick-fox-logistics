import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';

declare var H: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'Sick-Fox-logistics';

  private platform: any;
  public query: string;
  public sens01: string;
  public sens02: string;

  @ViewChild("map")
  public mapElement: ElementRef;

  public constructor() {
    this.query = "Starbucks";
    this.sens01 = "sens01";
    this.sens02 = "sens02";
    this.platform = new H.service.Platform({
      "app_id": "mYrOKG6Y0liojLdg0hvQ",
      "app_code": "At7q4TCeCI6BXaEWXk7Ksw"
    });
  }

  public ngOnInit() {  }

  public ngAfterViewInit() {
    let defaultLayers = this.platform.createDefaultLayers();
    let map = new H.Map(
      this.mapElement.nativeElement,
      defaultLayers.normal.map,
      {
        zoom: 10,
        center: { lat: 37.7397, lng: -121.4252 }
      }
    );
  }

}
