//Interface to store the user's search and allow them to be passed around between components
//  search = user's query
// refinedSearch = server's response

export interface Search {
    search: string;
    refinedSearch: string;
  }
  