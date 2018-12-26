//
//  ViewController.swift
//  Maps
//
//  Created by Michael Ziray on 8/30/18.
//  Copyright © 2018 BSU. All rights reserved.
//

import UIKit
import MapKit


class ViewController: UIViewController {

    @IBOutlet weak var mapView: MKMapView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        mapView.mapType = MKMapType.satelliteFlyover
        
        let boiseCenter = CLLocationCoordinate2D(latitude: 43.615313, longitude: -116.203233)
        
        mapView.setCenter(boiseCenter, animated: true)
        mapView.setRegion(MKCoordinateRegion(center: boiseCenter, span: MKCoordinateSpan(latitudeDelta: 0.0001, longitudeDelta: 0.0001)), animated: true)
    }
    

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

