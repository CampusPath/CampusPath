export declare type LngLat = [number, number];

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
    path: LngLat[];
    destination: Destination;
  }
}
