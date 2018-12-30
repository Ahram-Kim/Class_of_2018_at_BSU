import { Component } from '@angular/core';

import { AboutPage } from '../about/about';
import { HomePage } from '../home/home';
import { MapPage } from '../map/map';

@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {
  tab2Root = AboutPage;
  tab1Root = HomePage;
  tab3Root = MapPage;

  constructor() {

  }
}
