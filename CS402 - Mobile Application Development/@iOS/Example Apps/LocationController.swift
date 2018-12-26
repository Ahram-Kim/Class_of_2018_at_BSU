//
//  LocationController.swift
//  Project1
//
//  Created by Michael Ziray on 10/16/18.
//  Copyright © 2018 BSU. All rights reserved.
//

import Foundation
import CoreLocation

class LocationController: NSObject, CLLocationManagerDelegate {

    static var currentLocation:CLLocation = CLLocation()
    
    static var locationController:LocationController = LocationController()
    
    static var locationManager:CLLocationManager = CLLocationManager()
    
    class func startLocationMonitoring(){
        
        locationManager.delegate = locationController
        
        locationManager.requestWhenInUseAuthorization()
        
        locationManager.distanceFilter = 500.0
        
        locationManager.startUpdatingLocation()
    }
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        LocationController.currentLocation = locations[0]
        
        
    }
}
