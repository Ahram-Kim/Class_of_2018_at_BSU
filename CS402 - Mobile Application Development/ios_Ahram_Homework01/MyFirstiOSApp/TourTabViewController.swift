//
//  TourTabViewController.swift
//  MyFirstiOSApp
//
//  Created by ahramkim on 9/25/18.
//  Copyright © 2018 ahramkim. All rights reserved.
//

import UIKit
import MapKit
import CoreLocation

class TourTabViewController: UIViewController, UINavigationBarDelegate {

    @IBOutlet weak var navBar: UINavigationBar!
    @IBOutlet weak var mapView: MKMapView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        navBar.delegate = self
        
        mapView.mapType = MKMapType.satelliteFlyover
        
        let koreaLocation = CLLocationCoordinate2D(latitude: 37.5665, longitude: 126.9780)
        
        mapView.setCenter(koreaLocation, animated: true)
        mapView.setRegion(MKCoordinateRegion(center:koreaLocation, span: MKCoordinateSpan(latitudeDelta: 0.0001, longitudeDelta: 0.0001)), animated: true)
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    //MARK: UINavigationBarDelegate Methods
    func position(for bar: UIBarPositioning) -> UIBarPosition {
        return UIBarPosition.topAttached
    }

}
