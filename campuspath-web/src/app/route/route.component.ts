import { Component, OnInit } from '@angular/core';
import { Map } from 'maplibre-gl';

//TODO import nodes service or whatever we need to update paths
import { Nodes } from '../search';

@Component({
  selector: 'app-route',
  templateUrl: './route.component.html',
  styleUrls: ['./route.component.css']
})
export class RouteComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    

  }

  //TODO Parse coordinates from Nodes[]
  getCoordsFromNodes(nodes: Nodes[]): number[][] {
    var output: number[][] = [];

    for (let i = 0; i < nodes.length; i++) {
      var temp: number[] = [];
      temp.push(nodes[i].lat);
      temp.push(nodes[i].lng);
      output.push(temp);
    }

    return output;
  }
  
  //function sets the route for a given map using "nodes" as coordinates
  setRoute(map: Map, nodes:Nodes[]) {
    map.addSource('route', {
      'type': 'geojson',
      'data': {
        'type': 'Feature',
        'properties': {},
        'geometry': {
          'type': 'LineString',
          'coordinates': this.getCoordsFromNodes(nodes)
        }
      }
    });

    map.addLayer({
      'id': 'route',
      'type': 'line',
      'source': 'route',
      'layout': {
        'line-join': 'round',
        'line-cap': 'round'
      },
      'paint': {
        'line-color': '#888',
        'line-width': 8
      }
    });
  }
}
