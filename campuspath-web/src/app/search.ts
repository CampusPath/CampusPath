//Interface to store the user's search and allow them to be passed around between components
//  search = user's query
// buildingID = server's response for ID of building
// buildingName = server's response for name of the building

export interface Search {
  search: string;
  buildingID: string;
  buildingName: string;
}

//Interface to store the server's response (list of nodes) and allow them to be passed around between components
// lat = latitude
//lng = longitude
export interface Nodes {
  lat: number;
  lng: number;
}

export namespace V1 {

  export interface Campus {
    id: string;
    name: string;
    minLat: number;
    minLng: number;
    maxLat: number;
    maxLng: number;
  }

  export interface Destination {
    id: string;
    name: string;
    abbreviation: string;
  }

  export interface Route {
    distance: number;
    path: [number, number][]; // [lng, lat]
    destination: Destination;
  }
}
